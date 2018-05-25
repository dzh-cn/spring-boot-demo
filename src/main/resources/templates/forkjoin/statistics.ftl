<!DOCTYPE html>
<html style="height: 100%">
<head>
	<meta charset="utf-8">
	<style>
		div {
			height: 100%;
		}
	</style>
</head>
<body style="height: 100%; margin: 0">
	<div id="content-div"></div>
</body>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
<script type="text/javascript"
		src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>
<script type="text/javascript" src="http://localhost/static/resource/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">

	$(function () {
		gridByOnceMillisecond(getSpecimen(50, 50, 9));
	});

	function gridByOnceMillisecond(onceMilliseconds) {
		var param = JSON.stringify(onceMilliseconds);
		param = param.substr(1, param.length - 2);
		var dom = Customer_Echarts.getDom("");
		$.ajax({
			url: '/execute/result/allGroup',
			data: 'onceMillis=' + param,
			dataType: 'json',
			method: 'post',
			success: function (data) {
				var series = []
				var x = [];
				onceMilliseconds.forEach(function (v, i) {
					var y1 = [];
					var y2 = [];
					data[v].forEach(function (u, j) {
						if(i == 0) x[j] = u['taskCount'];
						y1[j] = u['totalMillis']
						y2[j] = u['fjTotalMillis']
					})
					// series[series.length] = Customer_Echarts.getOnceSeries(y1, 'co' + v)
					series[series.length] = Customer_Echarts.getOnceSeries(y2, 'fj' + v)
				});
				console.log(series);
				Customer_Echarts.grid(x, series, dom)
			}
		});
	}

	function getSpecimen(start, span, count) {
		var specimen = [];
		for (var i = 0; i < count; i++) {
			specimen[i] = span * i + start;
		}
		return specimen;
	}

	Customer_Echarts = {
		grid: function (x, series, dom) {
		    if(!dom) {
				dom = Customer_Echarts.getDom()
			}
			var myChart = echarts.init(dom);
			var option = {
				tooltip: {
					trigger: 'axis',
					axisPointer: {
					    type: 'line',
						axis: 'auto'
					},
					position: [10, 0]
				},
				xAxis: {
					name: '任务数',
					type: 'category',
					boundaryGap: false,
					data: x
				},
				yAxis: {
					name: '时长',
					type: 'value'
				},
				series: series
			};

			if (option && typeof option === "object") {
				myChart.setOption(option, true);
			}
		},
		getDom(name) {
			var $t = $("<div name="+ name +"></div>");
			$('#content-div').append($t);
			return $t[0];
		},
		getOnceSeries(data, name) {
			return {
				name: name,
				data: data,
				type: 'line'
			}
		}
	}
</script>
</html>