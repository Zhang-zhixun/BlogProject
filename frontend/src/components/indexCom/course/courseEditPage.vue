<template>
  <el-card class="box-card" style="width: 85vw">

    <template #header>
      <div class="card-header">
        <span style="font-weight: bold">修改用户信息</span>
        <el-button class="button" @click="updateUser()" size="large" type="primary">确认并修改</el-button>
      </div>
    </template>

    <div style="display: flex;height: 574px">
      <div style="width: 40%;">
        <el-form
            :model="form"
            :rules="rules"
            @validate="onValidate"
            ref="formRef"
        >

          <el-form-item prop="caid" label="用户账号" style="margin-top: 0px">
            <el-input v-model="form.caid" size="large" type="text" disabled placeholder="用户账号" >

            </el-input>
          </el-form-item>

          <el-form-item prop="ctname" label="用户姓名">
            <el-input v-model="form.ctname" size="large" type="text" placeholder="用户姓名">

            </el-input>
          </el-form-item>

          <el-form-item prop="ctIdCard" label="身份证号">
            <el-input v-model="form.ctIdCard" size="large" type="text" placeholder="身份证号码">

            </el-input>
          </el-form-item>

          <el-form-item prop="ctphone" label="手机号码">
            <el-input v-model="form.ctphone" size="large" type="number" placeholder="手机号码">

            </el-input>
          </el-form-item>

          <el-form-item prop="ctAddress" label="居住地址">
            <el-input v-model="form.ctAddress" size="large" type="text" placeholder="居住地址">

            </el-input>
          </el-form-item>

          <el-form-item prop="miCard" label="社保卡">
            <el-input disabled v-model="form.miCard" size="large" type="text" placeholder="社保卡">

            </el-input>
          </el-form-item>


        </el-form>
      </div>
      <div style="flex: 1;padding:0 10px">
        <el-image style="width: 100%;height: 100%"
                  src="https://ts1.cn.mm.bing.net/th/id/R-C.60361064d8eee12aa647695bf666bb38?rik=W663M7lXL03Y9A&riu=http%3a%2f%2fimg.mm4000.com%2ffile%2fe%2fe3%2f395f367325.jpg&ehk=mVwYGg59wm0g3RzSDpFZgv6EYwrdQQKlvIAtvNQsVTE%3d&risl=&pid=ImgRaw&r=0"/>
      </div>
    </div>
  </el-card>
</template>

<script setup>
import {reactive, ref} from 'vue'
import {get, post} from "@/net";
import router from "@/router";
import {ElMessage} from "element-plus";
import {useRoute} from "vue-router";

const form = reactive({
  caid: '',
  ctAddress: '',
  ctid: '',
  ctname: '',
  ctphone: '',
  ctIdCard: '',
  miCard: '',
})

const route = useRoute()


const tableData = ref([])

//获取订单信息
get('/api/index/findCustomersByCtId/' + route.params.id, (message) => {
  console.log(message)
  form.caid = message.customersAccount.caId
  form.ctAddress = message.ctAddress
  form.ctname = message.ctName
  form.ctphone = message.ctPhone
  form.ctid = message.ctId
  form.ctIdCard = message.ctIdCard
  form.miCard = message.medicalInsurance.miCard
  console.log(form)
})


//验证手机号码格式
const validatePhone = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请输入手机号'))
    //判断用户名是否以英文开头并不存在特殊字符
  } else if (!/^(13[0-9]|14[5-9]|15[0-35-9]|16[25-7]|17[0-8]|18[0-9]|19[0135689])[0-9]{8}$/.test(value)) {
    callback(new Error('手机号不合法,请重新输入'))
  } else {
    callback()
  }
}

//验证用户名格式
const validateMname = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请输入用户名'))
    //判断用户名是否以英文开头并不存在特殊字符
  } else if (!/^[_a-zA-Z0-9@]+$/.test(value)) {
    callback(new Error('不能存在特殊字符'))
  } else {
    callback()
  }
}

//验证地址格式
const validateAddress = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请输入居住地址'))
    //判断用户名是否以英文开头并不存在特殊字符
  } else if (!/^([\u4E00-\u9FA5A-Za-z0-9_]+(省|市|区|县|道|路|街|号)){2,}$/.test(value)) {
    callback(new Error('请输入正确的地址格式,比如福建省厦门市'))
  } else {
    callback()
  }
}

//验证姓名格式
const validateCTMname = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请输入用户姓名'))
    //判断用户名是否以英文开头并不存在特殊字符
  } else if (!/^(王|李|张|刘|陈|杨|黄|赵|吴|周|徐|孙|马|朱|胡|郭|何|高|林|罗|郑|梁|谢|宋|唐|许|韩|冯|邓|曹|彭|曾|肖|田|董|袁|潘|于|蒋|蔡|余|杜|叶|程|苏|魏|吕|丁|任|沈|姚|卢|姜|崔|钟|谭|陆|汪|范|金|石|廖|贾|夏|韦|傅|方|白|邹|孟|熊|秦|邱|江|尹|薛|闫|段|雷|侯|龙|史|黎|贺|顾|毛|郝|龚|邵|万|钱|覃|武|戴|孔|汤|庞|樊|兰|殷|施|陶|洪|翟|安|颜|倪|严|牛|温|芦|季|俞|章|鲁|葛|伍|申|尤|毕|聂|柴|焦|向|柳|邢|岳|齐|沿|梅|莫|庄|辛|管|祝|左|涂|谷|祁|时|舒|耿|牟|卜|路|詹|关|苗|凌|费|纪|靳|盛|童|欧|甄|项|曲|成|游|阳|裴|席|卫|查|屈|鲍|位|覃|霍|翁|隋|植|甘|景|薄|单|包|司|柏|宁|柯|阮|桂|闵|欧阳|解|强|丛|华|车|冉|房|边|辜|吉|饶|刁|瞿|戚|丘|古|米|池|滕|晋|苑|邬|臧|畅|宫|来|嵺|苟|全|褚|廉|简|娄|盖|符|奚|木|穆|党|燕|郎|邸|冀|谈|姬|屠|连|郜|晏|栾|郁|商|蒙|计|喻|揭|窦|迟|宇|敖|糜|鄢|冷|卓|花|艾|蓝|都|巩|稽|井|练|仲|乐|虞|卞|封|竺|冼|原|官|衣|楚|佟|栗|匡|宗|应|台|巫|鞠|僧|桑|荆|谌|银|扬|明|沙|薄|伏|岑|习|胥|保|和|蔺|水|云|昌|凤|酆|常|皮|康|元|平|萧|湛|禹|无|贝|茅|麻|危|骆|支|咎|经|裘|缪|干|宣|贲|杭|诸|钮|嵇|滑|荣|荀|羊|於|惠|家|芮|羿|储|汲|邴|松|富|乌|巴|弓|牧|隗|山|宓|蓬|郗|班|仰|秋|伊|仇|暴|钭|厉|戎|祖|束|幸|韶|蓟|印|宿|怀|蒲|鄂|索|咸|籍|赖|乔|阴|能|苍|双|闻|莘|贡|逢|扶|堵|宰|郦|雍|却|璩|濮|寿|通|扈|郏|浦|尚|农|别|阎|充|慕|茹|宦|鱼|容|易|慎|戈|庚|终|暨|居|衡|步|满|弘|国|文|寇|广|禄|阙|东|殴|殳|沃|利|蔚|越|夔|隆|师|厍|晃|勾|融|訾|阚|那|空|毋|乜|养|须|丰|巢|蒯|相|后|红|权逯|盖益|桓|公|万俟|司马|上官|夏侯|诸葛|闻人|东方|赫连|皇甫|尉迟|公羊|澹台|公冶|宗政|濮阳|淳于|单于|太叔|申屠|公孙|仲孙|轩辕|令狐|钟离|宇文|长孙|慕容|鲜于|闾丘|司徒|司空|亓官|司寇|仉|督|子车|颛孙|端木|巫马|公西|漆雕|乐正|壤驷|公良|拓跋|夹谷|宰父|谷粱|法|汝|钦|段干|百里|东郭|南门|呼延|归海|羊舌|微生|帅|缑|亢|况|郈|琴|梁丘|左丘|东门|西门|佘|佴|伯|赏|南宫|墨|哈|谯|笪|年|爱|仝|代)\s*[\u4e00-\u9fa5]{1,3}/.test(value)) {
    callback(new Error('请输入真实姓名'))
  } else {
    callback()
  }
}

//验证身份证格式
const validatectIdCard = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请输入用户身份证'))
    //判断用户名是否以英文开头并不存在特殊字符
  } else if (!/^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/.test(value)) {
    callback(new Error('请输入真实的身份证号码'))
  } else {
    callback()
  }
}


const rules = {
  ctphone: [
    {required: true, validator: validatePhone, trigger: ['blur', 'change']},
  ],
  caid: [
    {required: true, validator: validateMname, trigger: ['blur', 'change']},
    {min: 1, max: 8, message: '请输入用户名', trigger: ['blur', 'change']},
  ],
  ctname: [
    {required: true, validator: validateCTMname, trigger: ['blur']},
  ],
  ctAddress: [
    {required: true, validator: validateAddress, trigger: ['blur']},
  ],
  ctIdCard: [
    {required: true, validator: validatectIdCard, trigger: ['blur']},
  ]
}

const formRef = ref()
const updateUser = () => {
  formRef.value.validate((isVaild => {
    if (isVaild) {
      console.log(form)
      post('/api/index/updateCustomers', {
        caid: form.caid,
        ctAddress: form.ctAddress,
        ctId: form.ctid,
        ctName: form.ctname,
        ctPhone: form.ctphone,
        ctIdCard: form.ctIdCard,
      }, (message) => {
        router.push('/index/user')
        ElMessage.success(message)
      })
    } else {
      ElMessage.warning('请完整填写用户信息内容')
    }
  }))
}
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.el-form-item {
  margin-top: 40px;
}

</style>