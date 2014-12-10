var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var o2x = require('object-to-xml');
var _ = require('underscore')
var faker = require('faker')

var port = process.env.PORT || 8080; // set our port

var router = express.Router(); // get an instance of the express Router

router.get('/bar', function(req, res) {

  var cmd = req.query.cmd;

  var count = req.query.count || 1;
  var magic = req.query.magic == "true";
  var magicCountShow = req.query.magicCount == "true";


  var fieldsStr = req.query.fields || "";
  var fields = fieldsStr == "" ? null : fieldsStr.split(",")

  res.set('Content-Type', 'text/xml');
  if (cmd) {
    res.send(o2x({
      '?xml version="1.0" encoding="utf-8"?': null,
      result: createContent(cmd, count, magic, magicCountShow, fields)
    }));
  } else {
    res.status(500);
    res.send(o2x({
      '?xml version="1.0" encoding="utf-8"?': null,
      error: {
        message: "Missing command"
      }
    }))
  }

});


function createContent(cmd, count, magic, magicCountShow, fields) {
  var magicCount = magic ? parseInt(count / 3) : count

  var result = {
    cmd: cmd,
    count: count
  }

  if (magicCountShow) {
    _.extend(result, {
      magicCount: magicCount
    })
  }

  var documents = [];

  for (i = 0; i < magicCount; i++) {
    var document = {
      id: i,
      name: faker.name.findName()
    }


    var keys = []
    var values = []
    _.each(fields, function(e) {
      keys.push(e.trim())
      values.push(faker.name.findName())
    })

    _.extend(document, _.object(keys, values));

    documents.push(document)
  }

  if (magic) {
    _.extend(result, {
      magic: {
        documents: {
          document: documents
        }
      }
    })
  } else {
    _.extend(result, {
      documents: {
        document: documents
      }
    })
  }

  return result;
}


app.use('/foo', router);

app.listen(port);
console.log('Magic happens on port ' + port);
