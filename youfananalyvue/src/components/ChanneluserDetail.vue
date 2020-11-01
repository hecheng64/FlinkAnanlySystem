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
      <th>渠道</th>
      <th>浏览次数</th>
      <th>新增用户数</th>
      <th>小时活跃用户数</th>
      <th>天活跃用户数</th>
      <th>周活跃用户数</th>
      <th>月活跃用户数</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for ="(lable,index) in lables">
      <td>{{index+1}}</td>
      <td>{{lable.userName}}</td>
      <td>{{lable.deviceName}}</td>
      <td>{{lable.channelinfo}}</td>
      <td>{{lable.times}}</td>
      <td>{{lable.newusers}}</td>
      <td>{{lable.hourActivenums}}</td>
      <td>{{lable.dayActivenums}}</td>
      <td>{{lable.weekActivenums}}</td>
      <td>{{lable.monthActivenums}}</td>
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
      this.$http.post('http://127.0.0.1:9082/channel/usersDetailHour',{
        "timeinfo": timeinfo
      },{emulateJSON: true}).then((response) => {
        this.lables = response.body.userDetailList
      })
    }
  }
</script>
