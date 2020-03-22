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
package com.pekinsoft.northwind.about;

import com.pekinsoft.northwind.basic.Application;
import com.pekinsoft.northwind.basic.MenuProvider;
import com.pekinsoft.northwind.utils.Utils;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * Class providing menu for the About module.
 *
 * @author Jiří Kovalský &lt;jiri dot kovalsky at centrum dot cz&gt;
 */
public class AboutMenu implements MenuProvider {

    /**
     * Returns menu for the About module which can be added to the main application menu bar.
     *
     * @param desktop Main frame desktop where dialog with information about the Northwind Traders application will be opened
     * @return Initialized About menu with one About... menu item and registered action if the menu item is clicked
     */
    @Override
    public JMenu getMenu(JDesktopPane desktop) {
        JMenu menu = new JMenu("About");
        JMenuItem menuItem = new JMenuItem("About...");
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Application.log.enter(AboutMenu.class.getName(), "AboutMenu" + "_Clicked", evt);
                Application.log.debug("Creating an instance of the AboutDialog...");
                AboutDialog aboutDialog = new AboutDialog();
                desktop.add(aboutDialog);
                aboutDialog.pack();
                aboutDialog.setLocation(Utils.getCenterPoint(desktop.getSize(), aboutDialog.getSize()));
                aboutDialog.setVisible(true);
            }
        });
        menu.add(menuItem);
        return menu;
    }

}
