<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <div class="text-right">
      <div class="credits">
           Thực hiện bởi <a href="#">Nguyễn Hoàng Thiện</a>
        </div>
    </div>
    </section>
    <!--main content end-->
  </section>
  <!-- container section start -->

  <!-- javascripts -->
  <script src="<%=request.getContextPath()%>/templates/admin/js/jquery.js"></script>
  <script src="<%=request.getContextPath()%>/templates/admin/js/jquery-ui-1.10.4.min.js"></script>
  
  <script type="text/javascript" src="<%=request.getContextPath()%>/templates/admin/js/jquery-ui-1.9.2.custom.min.js"></script>
  <!-- bootstrap -->
  <script src="<%=request.getContextPath()%>/templates/admin/js/bootstrap.min.js"></script>
  <!-- nice scroll -->
  <script src="<%=request.getContextPath()%>/templates/admin/js/jquery.scrollTo.min.js"></script>
  <script src="<%=request.getContextPath()%>/templates/admin/js/jquery.nicescroll.js" type="text/javascript"></script>
  <!-- charts scripts -->
  <script src="<%=request.getContextPath()%>/templates/admin/assets/jquery-knob/js/jquery.knob.js"></script>
  <script src="<%=request.getContextPath()%>/templates/admin/js/jquery.sparkline.js" type="text/javascript"></script>
  <script src="<%=request.getContextPath()%>/templates/admin/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.js"></script>
  <script src="<%=request.getContextPath()%>/templates/admin/js/owl.carousel.js"></script>
  <!-- jQuery full calendar -->
  <<script src="<%=request.getContextPath()%>/templates/admin/js/fullcalendar.min.js"></script>
    <!-- Full Google Calendar - Calendar -->
    <script src="<%=request.getContextPath()%>/templates/admin/assets/fullcalendar/fullcalendar/fullcalendar.js"></script>
    <!--script for this page only-->
    <script src="<%=request.getContextPath()%>/templates/admin/js/calendar-custom.js"></script>
    <script src="<%=request.getContextPath()%>/templates/admin/js/jquery.rateit.min.js"></script>
    <!-- custom select -->
    <script src="<%=request.getContextPath()%>/templates/admin/js/jquery.customSelect.min.js"></script>
    <script src="<%=request.getContextPath()%>/templates/admin/assets/chart-master/Chart.js"></script>

    <!--custome script for all page-->
    <script src="<%=request.getContextPath()%>/templates/admin/js/scripts.js"></script>
    <!-- custom script for this page-->
    <script src="<%=request.getContextPath()%>/templates/admin/js/sparkline-chart.js"></script>
    <script src="<%=request.getContextPath()%>/templates/admin/js/easy-pie-chart.js"></script>
    <script src="<%=request.getContextPath()%>/templates/admin/js/jquery-jvectormap-1.2.2.min.js"></script>
    <script src="<%=request.getContextPath()%>/templates/admin/js/jquery-jvectormap-world-mill-en.js"></script>
    <script src="<%=request.getContextPath()%>/templates/admin/js/xcharts.min.js"></script>
    <script src="<%=request.getContextPath()%>/templates/admin/js/jquery.autosize.min.js"></script>
    <script src="<%=request.getContextPath()%>/templates/admin/js/jquery.placeholder.min.js"></script>
    <script src="<%=request.getContextPath()%>/templates/admin/js/gdp-data.js"></script>
    <script src="<%=request.getContextPath()%>/templates/admin/js/morris.min.js"></script>
    <script src="<%=request.getContextPath()%>/templates/admin/js/sparklines.js"></script>
    <script src="<%=request.getContextPath()%>/templates/admin/js/charts.js"></script>
    <script src="<%=request.getContextPath()%>/templates/admin/js/jquery.slimscroll.min.js"></script>
    <script>
      //knob
      $(function() {
        $(".knob").knob({
          'draw': function() {
            $(this.i).val(this.cv + '%')
          }
        })
      });

      //carousel
      $(document).ready(function() {
        $("#owl-slider").owlCarousel({
          navigation: true,
          slideSpeed: 300,
          paginationSpeed: 400,
          singleItem: true

        });
      });

      //custom select box

      $(function() {
        $('select.styled').customSelect();
      });

      /* ---------- Map ---------- */
      $(function() {
        $('#map').vectorMap({
          map: 'world_mill_en',
          series: {
            regions: [{
              values: gdpData,
              scale: ['#000', '#000'],
              normalizeFunction: 'polynomial'
            }]
          },
          backgroundColor: '#eef3f7',
          onLabelShow: function(e, el, code) {
            el.html(el.html() + ' (GDP - ' + gdpData[code] + ')');
          }
        });
      });
    </script>

</body>

</html>
