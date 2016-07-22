# Mengular v3.2

Hello there! Mengular is a framework for web developers, which includes 2 parts.

Let me introduce the 2 parts: JS Ajax Loaing and Java Template Engine.


## Part One: JS Ajax Loading

**JS Ajax Loading** of Mengular framework is a ajax loading framwork in web broswer, aiming to provide a easy and flexible way to load JSON array in your web page. 
###Loaing CSS and JS Files
At first we shold import js file and css file to our HTML document.

	<link href="static/css/mengular.css" rel="stylesheet">
	<script src="static/js/jquery.min.js"></script>
	<script src="static/js/mengular-2.0.js"></script>

The newest version is `mengular-2.0.js` which used features of jQuery, we are not going to introduced the old edition of 1.0. The jQuery framework is nessaseary, so make sure it is loaded before `mengular-2.0.js`.
###Prepare HTML Document
Then, we shuld add a template of mengular like this. We using placeholder `${key}$` to tell Mengular framework which you want to replace.

	<table id="student-table3" class="table">
		<thead>
			<tr>
				<th>Student Number</th>
				<th>Name</th>
				<th>Class Name</th>
				<th>Country</th>
				<th>Province</th>
				<th>City</th>
				<th>Birthday</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<tr id="${id}$" class="mengular-template student-table-template1">
				<td>${number}$</td>
				<td>${name}$</td>
				<td>${classname}$</td>
				<td>${hometown.country}$</td>
				<td>${hometown.province}$</td>
				<td>${hometown.city}$</td>
				<td>${birthday}$</td>
				<td><button class="btn btn-danger btn-xs">Delete</button></td>
			</tr>
			<tr id="${id}$" class="mengular-template student-table-template2">
				<td>${number}$</td>
				<td>${name}$</td>
				<td>${classname}$</td>
				<td>${hometown.country}$</td>
				<td>${hometown.province}$</td>
				<td>${hometown.city}$</td>
				<td>${birthday}$</td>
				<td><button class="btn btn-warning btn-xs">Delete</button></td>
			</tr>
		</tbody>
	</table>
###Load JSON Data
Remember the id of youe table, we will use `$("#student-table3 tbody")` to get the dom element of the body. Set class name of your template as `mengular-template` in order to tell Mengular framwork this is your template. You should also set the class of this template like `student-table-template1` or `mstudent-table-template2` in order to tell Mengular framework which template you want to use. We define data like:

	var testData=[{
			"id":"njww8jx8dy88xxxuixiwnw",
			"number":"20111235",
			"name":"Tim",
			"classname":"Computer Science and Technology 2",
			"hometown":{
				"country":"Japan",
				"province":"Kanagawa",
				"city":"Yokohama"
			},
			"birthday":"September, 20th, 1994"
		},{
			"id":"sdfkfy3833d8x9xwiwjs8x",
			"number":"20111236",
			"name":"Jack",
			"classname":"Computer Science and Technology 3",
			"hometown":{
				"country":"USA",
				"province":"CA",
				"city":"San Francisco"
			},
			"birthday":"March, 14th, 1993"
		},{
			"id":"ksify38333339xwiwjs8x",
			"number":"20111337",
			"name":"Json",
			"classname":"Computer Science and Technology 3",
			"hometown":{
				"country":"USA",
				"province":"CA",
				"city":"San Francisco"
			},
			"birthday":"March, 14th, 1993"
		}
	];

Then you can use js to load JSON Array for this table. In this demo, we use `student-table-template2` when the line number is a even number and use `student-table-template1` when the line number is a odd number.
​	
	for(var i in testData) {
		if(i==0) {
			$("#student-table3 tbody").mengular(".student-table-template1",testData[i]);
		} else {
			$("#student-table3 tbody").mengular(".student-table-template2",testData[i]);
		}
	}

In this code, we use `$("#student-table3 tbody")` to get dom element of the tbody. We use `mengular ` function which include two parameters to fill a row of this table. The fisrt parameter is selector of template like `".student-table-template1"` or `".student-table-template2"`, the second parameter is the JSON object you want to load. The key of JSON object is the palaceholder in the HTML document above. At last, Mengular create a table like this.

<table id="student-table3" class="table">
<thead>
	<tr>
		<th>Student Number</th>
		<th>Name</th>
		<th>Class Name</th>
		<th>Country</th>
		<th>Province</th>
		<th>City</th>
		<th>Birthday</th>
		<th>Delete</th>
	</tr>
</thead>
<tbody>
	<tr id="njww8jx8dy88xxxuixiwnw" class="student-table-template1">
		<td>20111235</td>
		<td>Tim</td>
		<td>Computer Science and Technology 2</td>
		<td>Japan</td>
		<td>Kanagawa</td>
		<td>Yokohama</td>
		<td>September, 20th, 1994</td>
		<td><button class="btn btn-danger btn-xs">Delete</button></td>
	</tr>
	<tr id="sdfkfy3833d8x9xwiwjs8x" class="student-table-template2">
		<td>20111236</td>
		<td>Jack</td>
		<td>Computer Science and Technology 3</td>
		<td>USA</td>
		<td>CA</td>
		<td>San Francisco</td>
		<td>March, 14th, 1993</td>
		<td><button class="btn btn-warning btn-xs">Delete</button></td>
	</tr>
	<tr id="ksify38333339xwiwjs8x" class="student-table-template2">
		<td>20111337</td>
		<td>Json</td>
		<td>Computer Science and Technology 3</td>
		<td>USA</td>
		<td>CA</td>
		<td>San Francisco</td>
		<td>March, 14th, 1993</td>
		<td><button class="btn btn-warning btn-xs">Delete</button></td>
	</tr>
  </tbody>
</table>
###Add New Rows or Clear Data Added
Now, we consider an other problem. If we want to reload this table with new data or just add some rows to this table, how to do this? We need not to worry about the templates will lost after using `mengular(template, data)` method because templates will always in our HTML document unless we delete them manually. So, we can directly reuse  `mengular(template, data)` method to add some rows anytime as we want. When we want to reload this table we can just call the method `mengularClear()` to remove rows added at first, them we can add some new row just like this: 
	
	//clear old rows
	$('#student-table3 tbody').mengularClear();
	//add some new rows
	for(var i in testData) {
		$('#student-table3 tbody').mengular(".student-table-template1",testData[i]);
	}
###Clear Template
Sometimes, we hope to remove template doucment after loading JSON array, just use `clearTemplate()` method.
	
	$("#student-table3 tbody").clearTemplate();
###Fill Text and Fill Value
We provided other two functions of `fillText(data)` and `fillValue(data)` which can fill text and value of dom. The parameter data is a JSON object like this:

	var data= {
		id-1: value-1,
		id-2: value-2,
		...
		id-n: value-n
	}  	
	
The key `id` is the id of dom element. The method `fillValue(data)` can only be used for `<input>` element.
## Part Two: Java Template Engine
**Java Template Engin** provids a simple way for using template engine. Compared to many Java Template Engine, Mengular is very simple because it only provided two methods. One of them is filling text by placeholder, the other is looping templates just like **JS Ajax Loading** introduced above.

Mengular Java Template Engine provides two functions of **Loop** and **Set Key**. **Loop** just like the function of JS Ajax Loaing introduced above, but it runs on the Java Web Server. **Set Key** can replace all the placehoders to values we want to replace. We can use this function in our Java source code to generate static HTML document and save this Document to your Web Application Folder.
###Prepare Template HTML Document
We need to use palceholder to indicate what we want to replace. For **Loop**, we use `#{key}#` as its placeholder, and for **Set key**, we use `#{key}` which is similar to the placeholder `${key}$` of **JS Ajax Loading** introduced above. 

Especially, we have to use flag `<!--mengular-start="template-id"-->` and `<!--mengular-end-->` which represent the start and end line of template document, in order to indicate the template before using **Loop**. There may be more than one template in our HTML document, thus, we use `template-id` to distingush them.
####Demo Document for Loop	

	<div id="comment-list" class="container">
    	<!--mengular-start="comment-list"-->
		<div id="#{cid}#" class="bs-callout">
			<div class="row">
				<div class="col-xs-12 col-sm-10">
					<h4 class="text-success">#{name}#</h4>
				</div>
				<div class="col-xs-12 col-sm-2">
					<h4 class="pull-right text-warning">#{date}#</h4>
				</div>
			</div>
			<hr>
			<p class="text-muted">#{content}#</p>
		</div>
		<!--mengular-end-->
	</div>	
####Demo Document for Set Key

	<h2 class="text-center">#{blog-title}</h2>
	<h4 class="text-center">Create in #{blog-date}, #{blog-readers} readers</h4>
At last, save your template in your web application folder.
###Init MengularDoucment
We provides two constructer for MengularDocument:

	/**
	 * Using default template file character encoding to init MengularDocument
	 * @param rootPath The root path of we Application
	 * @param templatePath The path of template HTML Document file
	 */
	public MengularDocument(String rootPath, int depth, String templatePath);
	
	/**
	 * Using custom template file character encoding to init MengularDocument
	 * @param rootPath The root path of we Application
	 * @param templatePath The path of template HTML Document file
	 * @param templateCharacterEncoding Custem template file character encoding
	 */
	public MengularDocument(String rootPath, int depth, String templatePath, String templateCharacterEncoding)  

We can get root path of web appliation in Servlet using

	//Servlet
	String rootPath=getServletConfig().getServletContext().getRealPath("/");
or in DWR using

	//DWR Framework
	String rootPath=WebContextFactory.get().getServletContext().getRealPath("/");
	
method `setLoop()` must called before method `setValue()`, that's beacuse `setLoop()` uses placeholder `#{}#` while `setValue()` uses placeholder `#{}`. If we call `setValue()` at fisrt, the placeholder for `setLoop()` will be destoried before it called.