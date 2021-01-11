const fs = require("fs");

const controller = {};
const myConnection = require("express-myconnection");

controller.list = (req, res) => {
  req.getConnection((err, conn) => {
    conn.query("SELECT * FROM inventory", (err, inventory) => {
      if (err) {
        res.json(err);
      }
      //console.log(inventory);
      res.render("inventory", {
        data: inventory,
      });
    });
  });
};

controller.save = (req, res) => {
  const data = req.body;
  var name = data.name;
  var sizep = data.sizep;
  var counter = data.counter;
  var price = data.price;
  var status = data.status;
  var description= data.description;

  if (req.files) {
    var image = req.files.image.name;
    var file = req.files.image;
    file.mv("public/images/" + image, function (err) {
      if (err) return res.status(500).send(err);
      var sql =
        "INSERT INTO `inventory`(`name`,`sizep`,`counter`,`price`, `status`, `description` ,`image`) VALUES ('" +
        name +
        "','" +
        sizep +
        "','" +
        counter +
        "','" +
        price +
        "','" +
        status +
        "','" +
        description +
        "','" +
        image +
        "')";
      req.getConnection((err, conn) => {
        conn.query(sql, (err, rows) => {
          res.redirect("/Inventario");
        });
      });
    });
  } else {
    image = "";
    var sql =
      "INSERT INTO `inventory`(`name`,`sizep`,`counter`,`price`, `status`, `description` ,`image`) VALUES ('" +
      name +
      "','" +
      sizep +
      "','" +
      counter +
      "','" +
      price +
      "','" +
      status +
      "','" +
      description +
      "','" +
      image +
      "')";
    req.getConnection((err, conn) => {
      conn.query(sql, (err, rows) => {
        res.redirect("/Inventario");
      });
    });
  }
};

controller.edit = (req, res) => {
  const id = req.params.id;
  req.getConnection((err, conn) => {
    conn.query("SELECT * FROM inventory WHERE id = ?", [id], (err, rows) => {
      res.render("inventory_edit", {
        data: rows[0],
      });
    });
  });
};

controller.update = (req, res) => {
  const id = req.params.id;
  const newCustomer = req.body;
  req.getConnection((err, conn) => {
    conn.query(
      "UPDATE inventory set ? where id = ?",
      [newCustomer, id],
      (err, rows) => {
        res.redirect("/Inventario");
      }
    );
  });
};

controller.delete = (req, res) => {
  const id = req.params.id;
  console.log(id);
  var image;
  req.getConnection((err, connection) => {
    connection.query(
      "SELECT image FROM inventory WHERE id = ?",
      [id],
      (err, rows) => {
        image = rows[0].image;
        if (image != "" && fs.existsSync("public/images/" + image)) {
          fs.rmSync("public/images/" + image);
        }
        //fs.unlink("public/images/" + image, function(){
        //});
      }
    );
  });
  req.getConnection((err, connection) => {
    connection.query(
      "DELETE FROM inventory WHERE id = ?",
      [id],
      (err, rows) => {
        res.redirect("/Inventario");
      }
    );
  });
};

module.exports = controller;
