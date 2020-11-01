<template>
  <div style="padding:20px;" id="app">
      <div class="panel panel-primary">
    <div class="panel-heading">ceshi</div>
    <table class="table table-bordered table-striped text-center">
    <thead>
    <tr>
      <th>序号</th>
       <th>时间</th>
      <th>浏览次数</th>
      <th>新增用户数</th>
      <th>天活跃用户数</th>
      <th>订单数量</th>
      <th>订单用户数</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for ="(lable,index) in lables">
      <td>{{index+1}}</td>
      <td>{{lable.timeinfoString}}</td>
      <td>{{lable.scantimes}}</td>
      <td>{{lable.newusers}}</td>
      <td>{{lable.dayActivenums}}</td>
      <td>{{lable.ordernums}}</td>
      <td>{{lable.orderusernums}}</td>
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
      this.$http.post('http://127.0.0.1:9082/gaiKuang/list').then((response) => {
        this.lables = response.body.list
      })
    }

  }
</script>
