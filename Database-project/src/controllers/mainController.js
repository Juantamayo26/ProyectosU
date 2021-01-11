//SELECT * FROM inventory WHERE status="Disponible";
//SELECT * FROM inventory WHERE status="En Camino";
const controller = {};
const myConnection = require("express-myconnection");

controller.disp = (req, res, next) => {
  const q = "Disponible";
  req.getConnection((err, conn) => {
    conn.query(
      "SELECT * FROM inventory WHERE status=?",
      [q],
      (err, vendors) => {
        if (err) {
          res.json(err);
        }
        req.disponible = vendors;
        next();
      }
    );
  });
};
controller.encami = (req, res, next) => {
  const q = "En Camino";
  req.getConnection((err, conn) => {
    conn.query(
      "SELECT * FROM inventory WHERE status=?",
      [q],
      (err, vendors) => {
        if (err) {
          res.json(err);
        }
        req.encamino = vendors;
        next();
      }
    );
  });
};

controller.deben= (req, res, next) => {
  const q = "En Camino";
  req.getConnection((err, conn) => {
    conn.query(
      "SELECT * FROM customer WHERE loans>0",
      (err, vendors) => {
        if (err) {
          res.json(err);
        }
        req.deben = vendors;
        next();
      }
    );
  });
};

controller.p = (req, res, next) => {
  const q = "En Camino";
  console.log("HOLA");
  req.getConnection((err, conn) => {
    conn.query(
      "SELECT * FROM inventory WHERE amount>0",
      (err, vendors) => {
        if (err) {
          res.json(err);
        }
        req.mejoresp = vendors;
        next();
      }
    );
  });
};

controller.c = (req, res, next) => {
  const q = "En Camino";
  req.getConnection((err, conn) => {
    conn.query(
      "SELECT * FROM customer WHERE amount>0",
      (err, vendors) => {
        if (err) {
          res.json(err);
        }
        req.mejoresc = vendors;
        next();
      }
    );
  });
};

controller.list = (req, res) => {
  res.render("main", {
    data: req.disponible,
    data2: req.encamino,
    data3: req.deben,
    data4: req.mejoresp,
    data5: req.mejoresc,
  });
};

module.exports = controller;
