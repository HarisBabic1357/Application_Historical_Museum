# Application_Historical_Museum
The application  was made in the EclipseIDE development environment. It is written in Java programming
language, and the database to which the application connects is made in MySQL
Workbench.

Trial data are located in file.zip and additional data are added through SQL script.After that, we connect on this database
through Java DataBase Connectivity (JDBC).

The application consists of 11 frames including the main initial. Application development is
arranged in multiple .java files. After launching the application, the main window opens and we can
select options for selecting individual database entities, such as exhibit, exhibit_type,
event, type of event, period, hall, employee, workplace. Click on each of these
options frames will open where it is possible to add, delete, update and print
the data themselves in tabular form.

Exceptions are options: list of exhibitors and participants of events where
it is not possible to update since these tables in the database contain foreign keys,
that is, they act like “children” in relation to the parents of foreign keys so for a change
column in the tables of these 2 options it is necessary to make changes in the tables or options
applications where columns are given as primary keys.
Only the addition of new data is done through the appropriate fields, and in the case of options where 
entity is given with foreign keys a drop-down list is created via  JComboBox  with the option already
existing entered values.
Below are shown main window and some of windows as selected options:

![glavni](https://user-images.githubusercontent.com/101056974/156991086-bff9a352-7c05-4150-91eb-235a5bca534e.PNG)

Option event:

![događaj](https://user-images.githubusercontent.com/101056974/156991185-bad3a460-e23d-4dc6-b59b-862295677a81.PNG)

Option exhibit:

![eksponaat](https://user-images.githubusercontent.com/101056974/156991057-b3db57c6-5b37-45cf-93e2-870c0946b4ba.PNG)

Option employee:

![zaposlenik](https://user-images.githubusercontent.com/101056974/156991559-da8294c0-74bf-41b0-99a7-4636841aa176.PNG)


