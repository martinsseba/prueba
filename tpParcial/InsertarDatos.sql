insert into TipoDocumento values(1,"DNI");

insert into ciudad values
(1,"Banfield"),
(2,"Temperley"),
(3,"Lanus");

insert into cliente values
(1,"Martins","Sebastian",34382254,1,1,null,null),
(2,"Gomez","Rodolfo",2015298,1,3,"20-2015298-04","Av. Peron 1134");

insert into articulo values
(1,"GO22","Goma",2.50),
(2,"RA4","Resma de hojas A4",52),
(3,"RC11","Resma de hojas Carta",62.50),
(4,"RO11","Resma de hojas oficio",79),
(5,"FX04","Talon factura X",44.25),
(6,"CA23","Carpeta",12.50),
(7,"CA44","Cuaderno A4",7.50),
(8,"CA33","Cuaderno A3",4),
(9,"MO11","Mochila",115),
(10,"CA69","Cartuchera",23.50);

insert into formadepago values
(1,"Efectivo",0,-5,0),
(2,"Tarjeta de debito",50,-5,0),
(3,"Tarjeta de credito 1 cuota",100,0,1),
(4,"Tarjeta de credito 3 cuotas",100,0,3),
(5,"Tarjeta de credito 6 cuotas",100,5,6);

insert into TipoFactura values
(1,"Factura A"),
(2,"Factura B");