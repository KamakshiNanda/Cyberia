(function ($) {
    "use strict";

    $.fn.extend({ 

      countdown100: function(options) {
        var defaults = {
          timeZone: "",
          endtimeYear: 2019,
          endtimeMonth: 12,
          endtimeDate: 19,
          endtimeHours: 0,
          endtimeMinutes: 0,
          endtimeSeconds: 0,
        }

        var options =  $.extend(defaults, options);

        return this.each(function() {
          var obj = $(this);
          var timeNow = new Date();

          var tZ = options.timeZone; console.log(tZ);
          var endYear = options.endtimeYear;
          var endMonth = options.endtimeMonth;
          var endDate = options.endtimeDate;
          var endHours = options.endtimeHours;
          var endMinutes = options.endtimeMinutes;
          var endSeconds = options.endtimeSeconds;

          if(tZ == "") {
            var deadline = new Date(endYear, endMonth - 1, endDate, endHours, endMinutes, endSeconds);
          } 
          else {
            var deadline = moment.tz([endYear, endMonth - 1, endDate, endHours, endMinutes, endSeconds], tZ).format();
          }

          if(Date.parse(deadline) < Date.parse(timeNow)) {
            var deadline = new Date(Date.parse(new Date())); 
          }
          
          var t = Date.parse(deadline) - Date.parse(new Date());
            
          var clock = $(obj).FlipClock(t/1000, {
            clockFace: 'DailyCounter',
            countdown: true
          });


        });
      }
    });

    

})(jQuery);