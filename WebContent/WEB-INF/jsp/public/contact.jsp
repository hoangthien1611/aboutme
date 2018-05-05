<%@include file="/templates/taglib.jsp" %>
<!-- contact -->
<div class="contact" id="contact">
	<div class="container">
	<span class="about-top-w3">Send me a message</span>
		<h4 class="title-w3ls">Contact Me</h4>
		<div class="col-md-5 contact-agileits-w3layouts">
			<form action="javascript:void(0)" method="post" id="form">
				<input type="text" name="fullname" id="fullname" placeholder="Name" required="" />
				<input type="email" name="email" id="email" placeholder="Email" required="" />
				<input type="text" name="phone" id="phone" placeholder="Phone Number" required=""/>
				<textarea name="content" id="content" placeholder="Message" required=""></textarea>
				 <div id="success" style="margin-bottom: 10px"></div>
				<input type="submit" value="Submit">
			</form>
			<h5 class="sub">Looking for Address</h5>
			<p><span>Location</span> : 54 Nguyễn Lương Bằng, Liên Chiểu, Đà Nẵng</p>
			<p><span>Phone</span> : +84 999 999 999</p>
			<p><span>Email</span><a href="#"> : hoangthien1611@gmail.com</a></p>
		</div>


		<div class="col-md-7 contact-map-right">
		<div id="map">
		<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d10843.72956460023!2d108.1489957235591!3d16.073927721675336!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x314218d0b39fdc29%3A0x40e97561059ade52!2zVHLGsOG7nW5nIMSR4bqhaSBo4buNYyBCw6FjaCBLaG9hIMSQw6AgTuG6tW5n!5e0!3m2!1sen!2s!4v1509716579031"></iframe>
		</div>


		</div>
	</div>
</div> 		
	<!-- //contact-ends-here -->
	<script>
                	
                    $(document).on('submit','#form', function(){
					var aname = $('#fullname').val();
					var aemail = $('#email').val();
					var aphone = $('#phone').val();
					var acontent = $('#content').val();
					
					$.ajax({
						url: "${pageContext.request.contextPath}/lien-he",
						type: 'POST',
						cache: false,
						data: {
							fullname: aname,
							email: aemail,
							phone: aphone,
							content: acontent
							},
						success: function(data){
							$('#success').html(data);

						},
						error: function (){
							alert("Có lỗi trong quá trình xử lý!");
						}
					});
					});
			</script>