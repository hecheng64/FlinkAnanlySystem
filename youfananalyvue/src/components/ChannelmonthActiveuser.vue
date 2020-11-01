<template>
  <div style="padding:20px;" id="app">
      <div class="panel panel-primary">
    <div class="panel-heading">ceshi</div>
    <x-chart id="high1" class="high" :option="option1"></x-chart>
    <x-chart id="high2" class="high" :option="option2"></x-chart>
  </div>
  </div>
</template>
<script>
  // 导入chart组件
  var myvue = {};
  import XChart from './charts'
  export default {
    data() {
      return {
        option1:{
          title: {
            text: '渠道小时级新增用户趋势'
          },
          yAxis: {
            title: {
              text: '新增人数'
            }
          },
          legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle'
          },
          xAxis: {
            categories: ['2010','2010','2010','2010','2010','2010','2010','2010'],
            crosshair: true
          },
          series: [{
            name: 'pc端',
            data: [43934, 52503, 57177, 69658, 97031, 119931, 137133, 154175]
          }, {
            name: 'app端',
            data: [24916, 24064, 29742, 29851, 32490, 30282, 38121, 40434]
          }, {
            name: '小程序端',
            data: [11744, 17722, 16005, 19771, 20185, 24377, 32147, 39387]
          }]
        },
        option2:{
          title: {
            text: '渠道分钟级新增用户趋势'
          },
          yAxis: {
            title: {
              text: '新增人数'
            }
          },
          legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle'
          },
          xAxis: {
            categories: ['2010','2010','2010','2010','2010','2010','2010','2010'],
            crosshair: true
          },
          series: [{
            name: 'pc端',
            data: [43934, 52503, 57177, 69658, 97031, 119931, 137133, 154175]
          }, {
            name: 'app端',
            data: [24916, 24064, 29742, 29851, 32490, 30282, 38121, 40434]
          }, {
            name: '小程序端',
            data: [11744, 17722, 16005, 19771, 20185, 24377, 32147, 39387]
          }]
        },
      }
    },
    beforeCreate:function(){
      myvue = this;
    },created(){
      this.$http.post('http://127.0.0.1:9082/channel/monthActivenumsMinute').then((response) => {
        this.option1 = {
          title: {
            text: '渠道分钟级月活跃用户趋势'
          },
          yAxis: {
            title: {
              text: '月活跃人数'
            }
          },
          legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle'
          },
          xAxis: {
            categories: response.body.xlist,
            crosshair: true
          },
          series: response.body.newuserslist
        }
      }),
        this.$http.post('http://127.0.0.1:9082/channel/monthActivenumsHour').then((response) => {
          this.option2 = {
            title: {
              text: '渠道小时级月活跃用户趋势'
            },
            yAxis: {
              title: {
                text: '月活跃人数'
              }
            },
            legend: {
              layout: 'vertical',
              align: 'right',
              verticalAlign: 'middle'
            },
            xAxis: {
              categories: response.body.xlist,
              crosshair: true
            },
            series: response.body.newuserslist,
            plotOptions: {
              series: {
                cursor: 'pointer',
                events: {
                  click: function(e) {
                    alert(e.point.category);
                    myvue.$router.push({name:'channeluserDetail',params:{timeinfo:e.point.category}})
                  }
                }
              }
            }
          }
        })
    },
    components: {
      XChart
    }
  }
</script>
