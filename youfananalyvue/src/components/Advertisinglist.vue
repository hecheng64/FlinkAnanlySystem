<template>
  <div style="padding:20px;" id="app">
      <div class="panel panel-primary">
    <div class="panel-heading">ceshi</div>
    <table class="table table-bordered table-striped text-center">
    <thead>
    <tr>
      <th>序号</th>
       <th>广告名称</th>
      <th>商品名称</th>
      <th>趋势</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for ="(lable,index) in lables">
      <td>{{index+1}}</td>
      <td>{{lable.adName}}</td>
      <td>{{lable.productName}}</td>
      <td><a href="javascript:void();" v-on:click="adQushiTimes(lable.adId,lable.productId)">点击趋势</a>||<a href="javascript:void();" v-on:click="adQushiUsers(lable.adId,lable.productId)">用户趋势</a>||<a href="javascript:void();" v-on:click="adQushiZhuanhualv(lable.adId,lable.productId)">转化率趋势</a></td>
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
      this.$http.post('http://127.0.0.1:9082/advertising/listAdvertisingBy').then((response) => {
        this.lables = response.body.advertisingList
      })
    },methods:{
      adQushiTimes:function(adId,productId){
        myvue.$router.push({name:'advertisingTimes',params:{adId:adId,productId:productId}})
      },
      adQushiUsers:function(adId,productId){
        myvue.$router.push({name:'advertisingUsers',params:{adId:adId,productId:productId}})
      },
      adQushiZhuanhualv:function(adId,productId){
        myvue.$router.push({name:'advertisingUsersZhl',params:{adId:adId,productId:productId}})
      }
    }

  }
</script>
