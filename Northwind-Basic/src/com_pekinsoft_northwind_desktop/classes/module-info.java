/*
 * Copyright (C) 2020 PekinSOFT Systems
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
 * 
 * *****************************************************************************
 * *****************************************************************************
 *  Project    :   Northwind-Basic
 *  Class      :   module-info.java
 *  Author     :   Sean Carrick
 *  Created    :   Mar 11, 2020 @ 5:33:46 PM
 *  Modified   :   Mar 11, 2020
 *  
 *  Purpose:
 *  
 *  Revision History:
 *  
 *  WHEN          BY                  REASON
 *  ------------  ------------------- ------------------------------------------
 *  Mar 11, 2020  Sean Carrick        Initial creation.
 * *****************************************************************************
 */

module com_pekinsoft_northwind_desktop {
    requires java.desktop;
    requires java.logging;
    requires com_pekinsoft_northwind_basic;
    
    exports com.pekinsoft.northwind.desktop;
}
