# Overview
Northwind Traders Complete Accounting Project is being designed to fill a niche for accounting packages in an industry that has vastly
different needs for financial breakdown of information - the trucking industry.

The trucking industry is a *per mile* industry, where the only financial information that matters is based upon the number of miles a 
unit may travel. These miles are either logged as __empty__ or __loaded__, depending upon whether the unit is hauling a load from 
Point A to Point B, or *deadheading* from Point B to Point C to pick up another load. Regardless of whether the truck is loaded or 
empty, there are multiple costs involved in the unit movement, such as fuel, maintenance, tolls/parking, etc. Even when the unit is 
not actively hauling a load, if it is moving, it is incurring these costs. This is why the financial breakdown that makes the most 
sense to the trucking industry is the *per mile* breakdown. While empty, units typically get higher fuel economy and lower maintenance 
costs than when loaded. However, every mile moved costs the company money. All of the expenses that have been mentioned so far don't 
even take into account the pay for the driver of the unit, who is usually paid per mile when the unit is empty, as well as when the 
unit is loaded.

Northwind Traders Complete Accounting Project aims to answer this lack of software that tracks revenue and expenses *per mile*.

## The Development System
Northwind Traders Complete Accounting Project is being developed using the Java&trade; Programming Language, as it is system 
independent.
By using Java&trade; we will be able to design and code the project once and it will be able to be used on any operating system that 
supports a Java&trade; Virtual Machine (JVM).

Furthermore, the Project will be designed in a __*modular*__ fashion. In this way, end users will only need to purchase those portions 
of the application that make sense for their business setup and structure. For this reason, not only are we developing the Project in 
the Java&trade; Programming Language, but we are using Java 12, which is modular by design.

### Basic Edition
Northwind Traders Complete Accounting Basic Edition is focused on the single-truck owner/operator truck driver. This system will have 
all of the features that an owner/operator will need to manage his/her company and know his/her company's numbers, down to the mile.

The modules that make up the Basic Edition are:
* Base Module - The entry and configuration module for the application.
* Customer Mangement Module - Provides the means of storing and locating customers that are shipping or receiving the loads.
* Vehicle/Unit Management Module - Provides a way for the owner/operator to maintain the information for their truck(s) and trailer(s).
* Loads Module - The handling of all load information.
* Accounting Module - The module that performs all of the accounting calculations for the application.
* General Ledger Module - The module that handles the entry of financial information into the application.
* Service Management Module - Manages and maintains records of repairs and maintenance on the vehicles/units.
* Reporting Module - Provides all of the reports the system is set up to create.
