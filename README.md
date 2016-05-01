# Mengular


Hello there! Mengular is a framework for web developers, which includes 2 parts.

Let me introduce the 2 parts: JS Ajax Loaing and Java Template Engine.


## Part One: JS Ajax Loading

**JS Ajax Loading** of Mengular framework is a ajax loading framwork in web broswer, aiming to provide a easy and flexible way to load JSON array in your web page. At first, you shuld add a template of mengular like this. We using placeholder `${key}$` to tell Mengular framework which you want to replace.

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

Remenber the id of youe table, we will use `$("#student-table3 tbody")` to get the dom element of the body. Set class name of your template as `mengular-template` in order to tell Mengular framwork this is your template. You should also set the class of this template like `student-table-template1` or `mstudent-table-template2` in order to tell Mengular framework which template you want to use. We define data like:

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
		</tr><tr id="sdfkfy3833d8x9xwiwjs8x" class="student-table-template2">
			<td>20111236</td>
			<td>Jack</td>
			<td>Computer Science and Technology 3</td>
			<td>USA</td>
			<td>CA</td>
			<td>San Francisco</td>
			<td>March, 14th, 1993</td>
			<td><button class="btn btn-warning btn-xs">Delete</button></td>
		</tr><tr id="ksify38333339xwiwjs8x" class="student-table-template2">
			<td>20111337</td>
			<td>Json</td>
			<td>Computer Science and Technology 3</td>
			<td>USA</td>
			<td>CA</td>
			<td>San Francisco</td>
			<td>March, 14th, 1993</td>
			<td><button class="btn btn-warning btn-xs">Delete</button></td>
		</tr></tbody>
</table>

## Part Two: Java Template Engine
