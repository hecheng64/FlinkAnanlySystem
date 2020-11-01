<template>
  <div style="padding:20px;" id="app">
      <div class="panel panel-primary">
    <div class="panel-heading">ceshi</div>
    <table class="table table-bordered table-striped text-center">
    <thead>
    <tr>
      <th>序号</th>
       <th>商品类别名称</th>
      <th>趋势</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for ="(lable,index) in lables">
      <td>{{index+1}}</td>
      <td>{{lable.productTypeName}}</td>
      <td><a href="javascript:viod()" v-on:click="productTypeQS(lable.id)">商品类别趋势</a></td>
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
      this.$http.post('http://127.0.0.1:9082/tuangouInfo/listProductTypeBy').then((response) => {
        this.lables = response.body.productTypeList
      })
    },methods:{
      productTypeQS:function(id){
        myvue.$router.push({name:'tuangouProductTypeTimes',params:{id:id}})
      }
    }

  }
</script>
