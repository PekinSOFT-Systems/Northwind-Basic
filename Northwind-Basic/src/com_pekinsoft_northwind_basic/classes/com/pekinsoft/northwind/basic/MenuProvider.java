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
package com.pekinsoft.northwind.basic;

import javax.swing.JDesktopPane;
import javax.swing.JMenu;

/**
 * Interface for classes providing top level menu in main frame of the Northwind Traders.
 *
 * @author Jiří Kovalský
 */
public interface MenuProvider {

    /**
     * Returns JMenu object which will be registered in the menu bar of the main frame of the Northwind Traders.
     *
     * @param desktop Desktop pane where user interface controlled by the created menu will be displayed.
     * @return Fully initialized menu with items and actions to be registered in the main frame.
     */
    public JMenu getMenu(JDesktopPane desktop);

}
