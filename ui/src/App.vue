<script setup>
import {ref} from "vue";
import {User,Lock} from "@element-plus/icons-vue";
const isregister=ref(true)
</script>

<template>
  <div>
    test
  </div>
  <el-row class="login-page">
    <el-col :span="12" class="background"></el-col>
    <el-col :span="6" :offset="3" class="form">
      <h1></h1>
      <!--注册-->
      <el-form  ref="form" size="large" autocapitalize="off" v-if="isregister">
        <el-form-item>
          <h1>注册</h1>
        </el-form-item>
        <el-form-item>
          <el-input :prefix-icon="User" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input
              :prefix-icon="Lock"
               placeholder="请输入新密码"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-input :prefix-icon="Lock" placeholder="请再次输入密码"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button class="button" type="primary" auto-insert-space>注册</el-button>
        </el-form-item>
        <el-form-item class="flex">
          <el-link type="info" :underline="false" @click="isregister = false">
             返回
          </el-link>
        </el-form-item>
      </el-form>
      <!--登录-->
        <el-form ref="form" size="large" autocapitalize="off" v-else>
          <el-form-item>
            <h1>登录</h1>
          </el-form-item>
          <el-form-item>
            <el-input :prefix-icon="User" placeholder="请输入用户名"></el-input>
          </el-form-item>
          <el-form-item>
            <el-input :prefix-icon="Lock" placeholder="请输入密码"></el-input>
          </el-form-item>
          <el-form-item  class="flex">
            <el-checkbox>记住我</el-checkbox>
            <div class="flex">
              <el-link align-right="true" type="primary" :underline="false">忘记密码？</el-link>
            </div>
          </el-form-item>
          <el-form-item>
            <el-button class="button" type="primary" auto-insert-space>登录</el-button>
          </el-form-item>
          <el-form-item class="flex">
            <el-link type="info" :underline="false" @click="isregister=true">
            注册
            </el-link>
          </el-form-item>
        </el-form>
    </el-col>
  </el-row>

</template>

<style lang="css" scoped>
.login-page {
  height: 100vh;
  background-color: #fff;

  .background {
    background: url('@/assets/LOGO.jpg') no-repeat 60% center / 700px auto;
    border-radius: 0 20px 20px 0;
  }

  .form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    user-select: none;

    .button {
      width: 100%;
    }

    .flex {
      margin-left: auto;
    }
  }
}
</style>
