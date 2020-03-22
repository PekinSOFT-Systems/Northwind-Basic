/*
 * Copyright (C) 2020 Jiří Kovalský
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
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Singleton for managing repository of additional Northwind Traders modules
 *
 * @author Jiří Kovalský &lt;jiri dot kovalsky at centrum dot cz&gt;
 */
public class ModulesManager {

    /**
     * Single instance of the custom Northwind Traders modules manager
     */
    private static ModulesManager manager;

    /**
     * Array with all custom Northwind Traders modules
     */
    private ArrayList<Module> modules = new ArrayList<Module>();

    /**
     * Returns the only available instance of this modules manager
     *
     * @return Default instance of the Northwind Traders modules manager
     */
    public static ModulesManager getDefault() {
        if (manager == null) {
            manager = new ModulesManager();
        }
        return manager;
    }

    /**
     * Loads all custom *.jar modules from default Northwind Traders directory e.g. /home/cesilko/.northwind/modules
     */
    public void loadModules() {
        File[] modulesDir = new File(Application.TOP_LEVEL_DIR + Application.FILE_SEPARATOR + "modules").listFiles();
        for (File file : modulesDir) {
            if (file.isFile() & file.getName().endsWith(".jar")) {
                Module module = Module.loadModule(file);
                if (module != null) {
                    modules.add(module);
                }
            }
        }
    }

    /**
     * Returns iterator with all loaded Northwind Traders custom modules.
     *
     * @return Iterator with all custom Northwind Traders modules
     */
    public Iterator<Module> getModules() {
        return modules.iterator();
    }
}
