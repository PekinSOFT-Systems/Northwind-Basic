/*
 * Copyright (C) 2020 cesilko
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.pekinsoft.northwind.basic.modules;

import com.pekinsoft.northwind.basic.Application;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

/**
 * Class representing an additional Northwind Traders module.
 *
 * @author Jiří Kovalský &lt;jiri dot kovalsky at centrum dot cz&gt;
 */
public class Module {

    /**
     * Identifier of the Northwind Traders module
     */
    private final String name;

    /**
     * Module version in major.minor format e.g. 1.2
     */
    private final String version;

    /**
     * Array of additional classes this module provides
     */
    private final Class[] classes;

    /**
     * Private constructor of the module.
     *
     * @param name Module identifier
     * @param version Module specification version e.g. 1.2
     * @param classes Classes provided by this module
     */
    private Module(String name, String version, Class[] classes) {
        this.name = name;
        this.version = version;
        this.classes = classes;
    }

    /**
     * Loads module from given *.jar file identifier.
     *
     * @param file Handler of *.jar file to load module from
     * @return Loaded module or null in case of I/O or class loader errors.
     */
    public static Module loadModule(File file) {
        try {
            ZipFile jarFile = new ZipFile(file);
            ZipEntry manifest = jarFile.getEntry("manifest.xml");
            InputStream reader = jarFile.getInputStream(manifest);
            DocumentBuilderFactory xmlReaderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = xmlReaderFactory.newDocumentBuilder();
            Document xmlDocument = documentBuilder.parse(reader);

            String name = xmlDocument.getElementsByTagName("name").item(0).getTextContent();
            String version = xmlDocument.getElementsByTagName("version").item(0).getTextContent();
            String masterClassName = xmlDocument.getElementsByTagName("masterClass").item(0).getTextContent();

            URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{file.toURI().toURL()});
            Class<?> masterClass = urlClassLoader.loadClass(masterClassName);
            Class[] classes = new Class[]{masterClass};
            System.out.println(name + " [" + version + "] module loaded.");
            return new Module(name, version, classes);
        } catch (Exception ex) {
            Application.log.error(ex, "Cannot open custom Northwind module: " + file.getAbsolutePath());
            return null;
        }
    }

    /**
     * Returns instance of module class implementing given interface.
     *
     * @param interfaceName Name of interface to search module classes for
     * @return Instance of module class implementing specified interface
     */
    public Object getInstanceOf(String interfaceName) {
        for (Class clazz : classes) {
            Class[] interfaces = clazz.getInterfaces();
            for (Class classInterface : interfaces) {
                if (classInterface.getCanonicalName().equals(interfaceName)) {
                    Constructor<?> constructor;
                    try {
                        constructor = clazz.getConstructor();
                        Object instance = constructor.newInstance();
                        return instance;
                    } catch (Exception ex) {
                        Application.log.error(ex, "Cannot instantiate Northwind module class: " + clazz.getCanonicalName());
                        return null;
                    }
                }
            }
        }
        return null;
    }
    
    /**
     * Provides a {@code String} representation of the class.
     * 
     * @return 
     */
    @Override
    public String toString() {
        return this.name + "[ v. " + version + " ]";
    }
}
