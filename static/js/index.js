var testData=[
	{
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

$(document).ready(function() {
	megularFunction1();

	megularFucntion2();

	megularFucntion3();
});

function megularFunction1() {
	mengular1(testData,"#student-table1 tbody");
}

function megularFucntion2() {
	var selector="#student-table2 tbody";
	for(var i in testData) {
		if(i==0) 
			mengular2(testData[i],selector,"templateClass1");
		else
			mengular2(testData[i],selector,"templateClass2");
		$("#"+testData[i].id+" td button").click(function(){
			console.log($(this).parent().parent().attr("id"));
			$(this).parent().parent().remove();
		});
	}
}

function megularFucntion3() { 
	for(var i in testData) {
		if(i==0) 
			$("#student-table3 tbody").mengular(".student-table-template1",testData[i]);
		else
			$("#student-table3 tbody").mengular(".student-table-template2",testData[i]);
	}
};