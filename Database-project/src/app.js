const express = require("express");
const path = require("path");
const morgan = require("morgan");
const mysql = require("mysql");
const myConnection = require("express-myconnection");
const fileUpload = require('express-fileupload');
const pdf = require('express-pdf');
const passport = require('passport');
const flash = require('connect-flash');
const validator= require('express-validator');
const session = require('express-session');
const MySQLStore = require('express-mysql-session')(session);

const { database } = require('./keys');
const app = express();
require('./lib/passport');

//import routes
const customerRoutes = require("./routes/customer");
const inventoryRoutes = require("./routes/inventory");
const vendorsRoutes = require("./routes/vendors");
const main = require("./routes/main");
const billingRoutes = require("./routes/billing");
const log = require("./routes/log");

//settings
app.set("port", process.env.PORT || 3000);
app.set("view engine", "ejs");
app.use(morgan("dev")); //routes view
//multiplataform
app.set("views", path.join(__dirname, "views"));
//middlewares -> routes of the server
app.use(
  myConnection(
    mysql,
    {
      host: "localhost",
      user: "root",
      password: "123",
      port: 3306,
      database: "zapatos",
    },
    "single"
  )
);
app.use(session({
  secret: 'faztmysqlnodemysql',
  resave: false,
  saveUninitialized: false,
  store: new MySQLStore(database)
}));
app.use(express.urlencoded({ extended: false }));
app.use(fileUpload());
app.use(pdf);
app.use(express.static(path.join(__dirname, "../public")));
app.use(flash());
app.use(passport.initialize());
app.use(passport.session());
app.use(validator());
//app.use('/pdfFromHTMLString', function(req, res){
//    res.pdfFromHTML({
//        filename: 'generated.pdf',
//        html: path.resolve(__dirname, './template.html')
//    });
//});
// Global variables
app.use((req, res, next) => {
  app.locals.message = req.flash('message');
  app.locals.success = req.flash('success');
  app.locals.user = req.user;
  next();
});


//routes
app.use("/Clientes", customerRoutes);
app.use("/Inventario", inventoryRoutes);
app.use("/Proovedores", vendorsRoutes);
app.use("/", main);
app.use("/Facturacion", billingRoutes);
app.use("/Log", log);
app.use(require('./routes/authentication'));


app.listen(app.get("port"), () => {
  console.log("Server on port 3000");
});
