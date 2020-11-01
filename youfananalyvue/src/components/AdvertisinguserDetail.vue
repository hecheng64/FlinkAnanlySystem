<template>
  <div style="padding:20px;" id="app">
      <div class="panel panel-primary">
    <div class="panel-heading">ceshi</div>
    <table class="table table-bordered table-striped text-center">
    <thead>
    <tr>
      <th>序号</th>
       <th>用户名称</th>
      <th>用户终端类型</th>
      <th>商品名称</th>
      <th>点击次数</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for ="(lable,index) in lables">
      <td>{{index+1}}</td>
      <td>{{lable.userName}}</td>
      <td>{{lable.deviceName}}</td>
      <td>{{lable.productName}}</td>
      <td>{{lable.times}}</td>
    </tr>
    </tbody>
  </table>
</div>
    </div>
</template>
<script>
  // 导入chart组件
  var myvue = {};
  export default {
    data() {
      return {
        lables: [
          {'typename': '李磊', 'lablevalue': '25'},
          {'typename': '李磊', 'lablevalue': '25'}
        ]
      }
    },
    beforeCreate:function(){
      myvue = this;
    },created(){
      var timeinfo = this.$route.params.timeinfo
      var adId = this.$route.params.adId
      var productId = this.$route.params.productId

      this.$http.post('http://127.0.0.1:9082/channel/usersDetailHour',{
        "timeinfo": timeinfo,"adId":adId,"productId":productId
      },{emulateJSON: true}).then((response) => {
        this.lables = response.body.userDetailList
      })
    }
  }
</script>
