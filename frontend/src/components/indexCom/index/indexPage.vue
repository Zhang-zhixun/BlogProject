<template>
  <el-scrollbar>
    <div class="indexPage_container">
      <div style="display: flex">
        <div style="width: 40%;">
          <el-card shadow="always">
            <div style="height: 270px">
              <div>
                <el-row :gutter="20">
                  <el-col :span="8" align="center">
                    <el-avatar style="width: 100px;height: 100px"
                               src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsafe-img.xhscdn.com%2Fbw1%2F8f5a1a05-d149-459e-948b-91495da6b68f%3FimageView2%2F2%2Fw%2F1080%2Fformat%2Fjpg&refer=http%3A%2F%2Fsafe-img.xhscdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1686901677&t=7033df9572d03382ccaf6a310f250af1"
                    />
                  </el-col>

                  <el-col :span="16" style="line-height:70px;">
                    <h2 style="font-weight: bold">管理员@{{ store.auth.user.username }}</h2>
                  </el-col>
                </el-row>
                <el-divider/>
              </div>
              <div>
                <el-row :gutter="20">
                  <el-col :span="8" align="center">
                    <p style="font-weight: bold">上次登录时间：</p>
                  </el-col>

                  <el-col :span="16">
                    <p style="font-weight: bold">2023-06-01</p>
                  </el-col>

                  <el-col :span="8" align="center">
                    <p style="font-weight: bold">上次登录地点：</p>
                  </el-col>

                  <el-col :span="16">
                    <p style="font-weight: bold">福建省厦门市</p>
                  </el-col>
                </el-row>
              </div>
            </div>
          </el-card>

          <el-card shadow="always" style="margin-top: 20px;">

            <template #header>
              <div class="card-header">
                <span style="font-weight: bold">今日任务</span>
                <el-button class="button" text>添加</el-button>
              </div>
            </template>

            <div style="height: 164px">
              <div style="margin-top: 5px">
                今日打卡
                <el-progress status="success" :percentage="100" />
              </div>
              <div style="margin-top: 5px">
                药品检测
                <el-progress color="gold" :percentage="70" />
              </div>
              <div style="margin-top: 5px">
                订单检测
                <el-progress color="royalblue" :percentage="40" />
              </div>
              <div style="margin-top: 5px">
                用户跟踪
                <el-progress color="#ef0065" :percentage="20" />
              </div>
            </div>
          </el-card>
        </div>
        <div style="flex: 1;margin-left: 20px;">
          <div
              style="width: 32%;display: flex;background-color: white;border-radius: 10px;box-shadow: var(--el-box-shadow-light);float: left">
            <div
                style="width: 30%;height: 90px;background-color: royalblue;border-top-left-radius: 5px;border-bottom-left-radius: 5px;line-height: 140px"
                align="center">
              <el-icon style="font-size: 60px;color: white">
                <User/>
              </el-icon>
            </div>
            <div style="flex: 1;padding: 20px" align="center">
              <span style="font-family: 微软雅黑">999</span><br/>
              <span style="color: royalblue;font-weight: bold;font-family: 微软雅黑">用户访问数量</span>
            </div>
          </div>
          <div
              style="width: 32%;margin-left:10px;display: flex;background-color: white;border-radius: 10px;box-shadow: var(--el-box-shadow-light);float: left">
            <div
                style="width: 30%;height: 90px;border-top-left-radius: 5px;border-bottom-left-radius: 5px;background-color: darkslateblue;line-height: 140px"
                align="center">
              <el-icon style="font-size: 60px;color: white">
                <MessageBox/>
              </el-icon>
            </div>
            <div style="flex: 1;padding: 20px" align="center">
              <span style="font-family: 微软雅黑">999</span><br/>
              <span style="color: royalblue;font-weight: bold;font-family: 微软雅黑">系统消息</span>
            </div>
          </div>
          <div
              style="width: 32%;margin-left:10px;display: flex;background-color: white;border-radius: 10px;box-shadow: var(--el-box-shadow-light);float: left;">
            <div
                style="width: 30%;height: 90px;border-top-left-radius: 5px;border-bottom-left-radius: 5px;background-color: darkorange;line-height: 140px"
                align="center">
              <el-icon style="font-size: 60px;color: white">
                <Money/>
              </el-icon>
            </div>
            <div style="flex: 1;padding: 20px" align="center">
              <span style="font-family: 微软雅黑">999</span><br/>
              <span style="color: royalblue;font-weight: bold;font-family: 微软雅黑">订单数量</span>
            </div>
          </div>
          <div style="clear: left"></div>

          <div style="margin-top: 20px;width: 98%;">
            <el-card class="box-card" style="height: 500px">

                <template #header>
                  <div class="card-header">
                    <span style="font-weight: bold">代办事项</span>
                    <el-button class="button" text>添加</el-button>
                  </div>
                </template>
              <el-form :model="form" label-width="120px">
                <div class="text item" style="display: flex">
                  <div style="width: 30px">
                    <el-checkbox-group v-model="form.type">
                      <el-checkbox label="" name="type"/>
                    </el-checkbox-group>
                  </div>
                  <div style="flex:1;width:100%;line-height: 30px;">
                    <el-text tag="del">检查药品信息是否有过期</el-text>
                    <el-button class="button" type="primary" text style="margin-left: 60%">编辑</el-button>
                    <el-button class="button" type="primary" text style="margin-left: 1%">删除</el-button>
                  </div>
                </div>

                <div class="text item" style="display: flex">
                  <div style="width: 30px">
                    <el-checkbox-group v-model="form.type">
                      <el-checkbox label="" name="type"/>
                    </el-checkbox-group>
                  </div>
                  <div style="flex:1;width:100%;line-height: 30px;">
                    <el-text tag="del">检查药品信息是否有过期</el-text>
                    <el-button class="button" type="primary" text style="margin-left: 60%">编辑</el-button>
                    <el-button class="button" type="primary" text style="margin-left: 1%">删除</el-button>
                  </div>
                </div>

                <div class="text item" style="display: flex">
                  <div style="width: 30px">
                    <el-checkbox-group v-model="form.type">
                      <el-checkbox label="" name="type"/>
                    </el-checkbox-group>
                  </div>
                  <div style="flex:1;width:100%;line-height: 30px;">
                    <el-text tag="del">检查药品信息是否有过期</el-text>
                    <el-button class="button" type="primary" text style="margin-left: 60%">编辑</el-button>
                    <el-button class="button" type="primary" text style="margin-left: 1%">删除</el-button>
                  </div>
                </div>


                <div class="text item" style="display: flex">
                  <div style="width: 30px">
                    <el-checkbox-group v-model="form.type">
                      <el-checkbox label="" name="type"/>
                    </el-checkbox-group>
                  </div>
                  <div style="flex:1;width:100%;line-height: 30px;">
                    <el-text tag="del">检查药品信息是否有过期</el-text>
                    <el-button class="button" type="primary" text style="margin-left: 60%">编辑</el-button>
                    <el-button class="button" type="primary" text style="margin-left: 1%">删除</el-button>
                  </div>
                </div>


                <div class="text item" style="display: flex">
                  <div style="width: 30px">
                    <el-checkbox-group v-model="form.type">
                      <el-checkbox label="" name="type"/>
                    </el-checkbox-group>
                  </div>
                  <div style="flex:1;width:100%;line-height: 30px;">
                    <el-text tag="del">检查药品信息是否有过期</el-text>
                    <el-button class="button" type="primary" text style="margin-left: 60%">编辑</el-button>
                    <el-button class="button" type="primary" text style="margin-left: 1%">删除</el-button>
                  </div>
                </div>

                <div class="text item" style="display: flex">
                  <div style="width: 30px">
                    <el-checkbox-group v-model="form.type">
                      <el-checkbox label="" name="type"/>
                    </el-checkbox-group>
                  </div>
                  <div style="flex:1;width:100%;line-height: 30px;">
                    <el-text tag="del">检查药品信息是否有过期</el-text>
                    <el-button class="button" type="primary" text style="margin-left: 60%">编辑</el-button>
                    <el-button class="button" type="primary" text style="margin-left: 1%">删除</el-button>
                  </div>
                </div>

                <div class="text item" style="display: flex">
                  <div style="width: 30px">
                    <el-checkbox-group v-model="form.type">
                      <el-checkbox label="" name="type"/>
                    </el-checkbox-group>
                  </div>
                  <div style="flex:1;width:100%;line-height: 30px;">
                    <el-text tag="del">检查药品信息是否有过期</el-text>
                    <el-button class="button" type="primary" text style="margin-left: 60%">编辑</el-button>
                    <el-button class="button" type="primary" text style="margin-left: 1%">删除</el-button>
                  </div>
                </div>
              </el-form>
            </el-card>
          </div>
        </div>
      </div>

      <div style="margin-top: 20px">
        <el-row :gutter="20">
          <el-col :span="12">

            <div class="card">
              <div ref="goodsChart" style="width: 97%;height: 400px;background-color: white;box-shadow: var(--el-box-shadow-light);"></div>
            </div>

          </el-col>
          <el-col :span="12">

            <div class="card">
              <div ref="customerChart" style="width: 97%;height: 400px;background-color: white;box-shadow: var(--el-box-shadow-light);"></div>
            </div>


          </el-col>
        </el-row>
      </div>



    </div>


  </el-scrollbar>


</template>

<script setup type="text/javascript">

import {onMounted, reactive, ref} from "vue";
import {useStore} from "@/stores";
import * as echarts from 'echarts'

const store = useStore()

const form = reactive({
  name: '',
  region: '',
  date1: '',
  date2: '',
  delivery: false,
  type: [],
  resource: '',
  desc: '',
})



// 指定图表的配置项和数据
onMounted(() => {
  init()
})

const goodsChart = ref();
const customerChart = ref();


const init =()=> {
  const gChart = echarts.init(goodsChart.value);
  const cChart = echarts.init(customerChart.value);
  let option = {
    title: {
      text: '商品销售表',
      left:'45%',
      top:'20px'
    },
    // 鼠标移入显示的东西
    tooltip: {
      trigger: 'item',
      formatter: '{a} <br/>{b} : {c} ({d}%)'
    },
    legend: {
      top: 'bottom',
    },
    // toolbox: {
    //   show: true,
    //   feature: {
    //     mark: { show: true },
    //     // 点击小图标数据详情的显示与隐藏
    //     dataView: { show: true, readOnly: false },
    //     // 刷新图表按键的显示与隐藏
    //     restore: { show: true },
    //     // 保存图表按键的显示与隐藏
    //     saveAsImage: { show: true }
    //   }
    // },
    series: [
      {
        // 光标移入显示的东西
        name: '商品信息',
        type: 'pie',
        radius: [20, 140],
        center: ['50%', '50%'],
        top:'20px',
        roseType: 'radius',
        itemStyle: {
          // 图表圆角尺度
          borderRadius: 10
        },
        label: {
          // 控制图表各属性指示的显示与隐藏
          show: true
        },
        emphasis: {
          label: {
            // 鼠标移动到哪个模块 相应指示上数据的隐藏还是显示
            show: true
          }
        },
        data: [
          { value: 40, name: '板蓝根颗粒' },
          { value: 33, name: '氨酚咖黄敏片' },
          { value: 28, name: '999皮炎平润肤膏' },
          { value: 22, name: '酚麻美敏片' },
          { value: 20, name: '罗红霉素胶囊' },
          { value: 15, name: '金嗓子喉片' },
          { value: 12, name: '九芝堂桂林西瓜霜' },
          { value: 10, name: '999感冒药' }
        ]
      },
    ]
  };

  gChart.setOption(option)

  let option2 = {
    title: {
      text: '订单新增表',
      left:'center'
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'cross',
        label: {
          backgroundColor: '#6a7985'
        }
      }
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true
    },
    xAxis: [
      {
        type: 'category',
        boundaryGap: false,
        data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
      }
    ],
    yAxis: [
      {
        type: 'value'
      }
    ],
    series: [{
      data: [720, 932, 901, 934, 1290, 1330, 1320],
      type: 'line',
      smooth: true,
      areaStyle: {},
      show: true,
      position: 'top',
      label: {
      },

    }]
  };



  cChart.setOption(option2)
}





</script>

<style scoped>

.indexPage_container {
  padding: 10px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.box-card {

  margin-top: 20px;
}

</style>