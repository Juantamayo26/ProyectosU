const controller = {};
const myConnection = require("express-myconnection");
const path = require("path");
const ejs = require("ejs");
let pdf = require("html-pdf");

controller.list = (req, res) => {
  req.getConnection((err, conn) => {
    conn.query("SELECT * FROM customer", (err, customers) => {
      if (err) {
        res.json(err);
      }
      res.render("billing", {
        data: customers,
      });
    });
  });
};

controller.inventory = (req, res) => {
  const q = "Disponible";
  req.getConnection((err, conn) => {
    conn.query(
      "SELECT * FROM inventory WHERE status=? AND counter>0",
      [q],
      (err, inventory) => {
        if (err) {
          res.json(err);
        }
        res.render("billing2", {
          data: inventory,
        });
      }
    );
  });
};

////////ready ====================---------------------======================------------================------------===============
///NOMBRE DEL PRODUCTO
//TALLA
//PRECIO
//- 1 CANTIDAD
//SUMAR 1 +
//######CUSTOMER
//NOMBRE DEL COMPRADOR
//CC
//TELEFONO
//DIRECCION
//SUMAR 1 +
//
//
//
//
//UPDATE customer SET amount=? WHERE id = ?;
//UPDATE inventory SET amount=? WHERE id = ?;
//UPDATE inventory SET counter=? WHERE id = ?;

//controller.payment = (req, res) => {
//  const q = "Disponible";
//  const id1 = req.params.id1;
//  const id2 = req.params.id2;
//  console.log(id1);
//  console.log(id2);
//  req.getConnection((err, conn) => {
//    conn.query("SELECT * FROM inventory WHERE id=?", [q], (err, inventory) => {
//      if (err) {
//        res.json(err);
//      }
//    });
//  });
//};

///////////////////////////////////////////////////
//UPDATE customer SET amount=? WHERE id = ?;
//UPDATE inventory SET amount=? WHERE id = ?;
//UPDATE inventory SET counter=? WHERE id = ?;

controller.update3 = (req, res, next) => {
  var amount = 1;
  const id1 = req.params.id1;
  const id2 = req.params.id2;
  console.log(req.hola);
  req.getConnection((err, conn) => {
    conn.query("UPDATE inventory SET counter=? WHERE id = ?", [req.hola, id2], (err, vendors) => {
      if (err) {
        res.json(err);
      }
    });
  });
  next();
};

controller.update1 = (req, res, next) => {
  var amount = 1;
  const id1 = req.params.id1;
  req.getConnection((err, conn) => {
    conn.query("UPDATE customer SET amount=? WHERE id = ?", [amount, id1], (err, vendors) => {
      if (err) {
        res.json(err);
      }
      req.id1 = vendors;
      next();
    });
  });
};

controller.update2 = (req, res, next) => {
  var amount = 1;
  const id2 = req.params.id2;
  req.getConnection((err, conn) => {
    conn.query("select * FROM inventory WHERE id = ?", [id2], (err, vendors) => {
      if (err) {
        res.json(err);
      }
      req.hola = parseInt(vendors[0].counter)-1;
      console.log(req.hola);
      //console.log();
    });
  });
  req.getConnection((err, conn) => {
    conn.query("UPDATE inventory SET amount=? WHERE id = ?", [amount, id2], (err, vendors) => {
      if (err) {
        res.json(err);
      }
      req.id1 = vendors;
      next();
    });
  });
};



controller.id1 = (req, res, next) => {
  const id1 = req.params.id1;
  req.getConnection((err, conn) => {
    conn.query("SELECT * FROM customer WHERE id=?", [id1], (err, vendors) => {
      if (err) {
        res.json(err);
      }
      req.id1 = vendors;
      next();
    });
  });
};

controller.id2 = (req, res, next) => {
  const id2 = req.params.id2;
  req.getConnection((err, conn) => {
    conn.query("SELECT * FROM inventory WHERE id=?", [id2], (err, vendors) => {
      if (err) {
        res.json(err);
      }
      req.id2 = vendors;
      next();
    });
  });
};


//UPDATE0

///////////////////last

controller.payment = (req, res) => {
  ejs.renderFile(
    path.join(__dirname, "../views/", "payment.ejs"),
    {
      data1: req.id1[0],
      data2: req.id2[0],
    },
    (err, data) => {
      if (err) {
        res.send(err);
      } else {
        let options = {
          height: "9.25in",
          width: "8.5in",
          header: {
            height: "20mm",
          },
          footer: {
            height: "20mm",
          },
        };
        pdf.create(data, options).toFile("factura.pdf", function (err, data) {
          if (err) {
            res.send(err);
          } else {
            res.pdf(path.resolve(__dirname, "../../factura.pdf"));
          }
        });
      }
    }
  );
};

module.exports = controller;
