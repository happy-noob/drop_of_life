$(document).ready(function() {
	var base;
	$('#chooseImage').change(function() {
		run(this, function(data) {
			var point = data.lastIndexOf(",");
			base = data.substr(point);
			var arr = data.split(",");
			console.log(arr[1]);
			//console.log(data);
		});
		var filePath = $(this).val(), //获取到input的value，里面是文件的路径
			fileFormat = filePath.substring(filePath.lastIndexOf(".")).toLowerCase(),
			src = window.URL.createObjectURL(this.files[0]); //转成可以在本地预览的格式

		// 检查是否是图片
		if(!fileFormat.match(/.png|.jpg/)) {
			error_prompt_alert('上传错误,文件格式必须为：png/jpg');
			return;
		}
		$('#cropedBigImg').attr('src', src);
		$('#scr').css('display', 'block');

	});
	$("#back").click(function() {
		$('#scr').css('display', 'none');
	});
	$("#preloader").hide();
	$("#change").click(function() {

		var location = $("input[name='file']").val();
		var point = location.lastIndexOf(".");

		var type = location.substr(point);
		console.log(type);
		$("#prt").hide();
		$("#preloader").show();
		var data= {
			"base64str": base,
			"suffix": type
		};
		$.ajax({
			url: "http://yelsonsg.nat300.top/shaoguang_deblur/img/deblur",
			type: "post",
			dataType: "json",
			contentType: "application/json",
			data: JSON.stringify(data),
			// async: false,
			beforeSend: function() {
				$("#prt").hide();
				$("#preloader").show();
			},
			complete: function() {
				$("#preloader").hide();
			},
			success: function(datahhh) {
				$("#scr").hide();
				$("#preloader").hide();
				$(".info").hide();
				$(".con").hide();
				$(".container").hide();
				$(".small").hide();
				$(".main").append("<img class='result' src='" + datahhh.data.outputPath + "' alt='本系统处于测试状态，尚不稳定，用户可根据图片链接自行访问。"+datahhh.data.outputPath+"'><button id='next'>再次上传</button>");
			}
		});
	});

	function run(input_file, get_data) {
		/*input_file：文件按钮对象*/
		/*get_data: 转换成功后执行的方法*/
		if(typeof(FileReader) === 'undefined') {
			alert("抱歉，你的浏览器不支持 FileReader，不能将图片转换为Base64，请使用现代浏览器操作！");
		} else {
			try {
				/*图片转Base64 核心代码*/
				var file = input_file.files[0];
				//这里我们判断下类型如果不是图片就返回 去掉就可以上传任意文件
				if(!/image\/\w+/.test(file.type)) {
					alert("请确保文件为图像类型");
					return false;
				}
				var reader = new FileReader();
				reader.onload = function() {
					get_data(this.result);
				}
				reader.readAsDataURL(file);
			} catch(e) {
				alert('图片转Base64出错啦！' + e.toString())
			}
		}
	}
	$('body').on('click', '[id=next]', function() {
		//$(".main").empty();
		$("#next").hide();
		$(".result").hide();
		$(".info").show();
		$(".con").show();
		$(".container").show();
		$(".small").show();

	})
})