const express = require("express");
const path = require("path");
const app = express();

app.use(express.static(path.join(__dirname, "../dist")));

app.get("/*", function (_req, res) {
  res.sendFile(path.join(__dirname, "../dist", "index.html"));
});

app.listen(5003);
console.log("RUNNING ON PORT 5003");
