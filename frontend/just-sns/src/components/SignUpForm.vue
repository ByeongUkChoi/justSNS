<template>
  <b-form @submit.prevent="onSubmit" @reset="onReset">
    <b-form-group
      id="fieldset-1"
      label-cols-sm="4"
      label-cols-lg="3"
      content-cols-sm
      content-cols-lg="7"
      description=""
      label="이름"
      label-for="name"
      :invalid-feedback="`이름을 입력해주세요. (${this.length.min.name}자 이상 ${this.length.max.name}자 이하)`"
      ><b-form-input
        id="name"
        v-model="form.name"
        :state="verifyName"
        trim
      ></b-form-input>
    </b-form-group>
    <b-form-group
      label-cols-sm="4"
      label-cols-lg="3"
      content-cols-sm
      content-cols-lg="7"
      description=""
      label="ID"
      label-for="id"
      :invalid-feedback="idInvalidFeedback"
      ><b-form-input
        id="id"
        v-model="form.id"
        :state="verifyId"
        trim
      ></b-form-input>
    </b-form-group>
    <b-form-group
      id="fieldset-1"
      label-cols-sm="4"
      label-cols-lg="3"
      content-cols-sm
      content-cols-lg="7"
      description=""
      label="비밀번호"
      label-for="password"
      :invalid-feedback="passwordInvalidFeedback"
      ><b-form-input
        id="password"
        v-model="form.password"
        :state="verifyPassword"
        trim
      ></b-form-input>
    </b-form-group>
    <b-form-group
      id="fieldset-1"
      label-cols-sm="4"
      label-cols-lg="3"
      content-cols-sm
      content-cols-lg="7"
      description=""
      label="비밀번호 확인"
      label-for="id"
      :invalid-feedback="passwordCheckInvalidFeedback"
      ><b-form-input
        id="passwordCheck"
        v-model="form.passwordCheck"
        :state="verifyPasswordCheck"
        trim
      ></b-form-input>
    </b-form-group>
    <b-container>
      <b-button type="submit" variant="primary" :disabled="!enableSubmit"
        >Submit</b-button
      >
      <b-button type="reset" variant="danger" center>Reset</b-button>
    </b-container>
  </b-form>
</template>
<script>
import { duplicateCheck, signUp } from '@/api/auth';

export default {
  computed: {
    verifyName() {
      return this.checkLength('name');
    },
    verifyId() {
      if (!this.checkLength('id')) {
        return false;
      }
      this.checkDuplicateId();
      if (this.isDuplicateId) {
        return false;
      }
      return true;
    },
    idInvalidFeedback() {
      if (!this.checkLength('id')) {
        return `아이디를 입력해 주세요. (${this.length.min.id}자 이상 ${this.length.max.id}자 이하)`;
      }
      // id 중복 여부
      if (this.isDuplicateId) {
        return '아이디가 중복입니다.';
      }
      return '';
    },
    verifyPassword() {
      if (!this.checkLength('password')) {
        return false;
      }
      // TODO: password 정합성 검사
      return true;
    },
    passwordInvalidFeedback() {
      if (!this.checkLength('password')) {
        return `비밀번호를 입력해 주세요. (${this.length.min.password}자 이상 ${this.length.max.password}자 이하)`;
      }
      // TODO: password 정합성 검사
      return '';
    },
    verifyPasswordCheck() {
      // 패스워드가 올바르지 않은 경우
      if (!this.verifyPassword) {
        return false;
      }
      // 패스워드와 일치하지 않은 경우
      if (!this.equalPasswordCheck()) {
        return false;
      }
      return true;
    },
    passwordCheckInvalidFeedback() {
      if (!this.checkLength('passwordCheck')) {
        return `비밀번호를 입력해 주세요. (${this.length.min.password}자 이상 ${this.length.max.password}자 이하)`;
      }
      if (!this.equalPasswordCheck()) {
        return '비밀번호가 다릅니다.';
      }
      // TODO: password 정합성 검사
      return '';
    },
    enableSubmit() {
      if (
        this.verifyName &&
        this.verifyId &&
        this.verifyPassword &&
        this.verifyPasswordCheck
      ) {
        return true;
      }
      return false;
    },
  },
  data() {
    return {
      length: {
        min: {
          name: 2,
          id: 3,
          password: 4,
          passwordCheck: 4,
        },
        max: {
          name: 20,
          id: 20,
          password: 20,
          passwordCheck: 20,
        },
      },
      form: {
        name: '',
        id: '',
        password: '',
        passwordCheck: '',
      },
      isDuplicateId: false,
    };
  },
  methods: {
    checkLength(type) {
      if (this.form[type].length < this.length.min[type]) {
        return false;
      } else if (this.form[type].length > this.length.max[type]) {
        return false;
      }
      return true;
    },
    async checkDuplicateId() {
      try {
        await duplicateCheck(this.form.id);
        this.isDuplicateId = false;
      } catch (error) {
        this.isDuplicateId = true;
      }
    },
    equalPasswordCheck() {
      return this.form.password === this.form.passwordCheck;
    },
    async onSubmit() {
      if (!this.enableSubmit) {
        return;
      }
      const payload = {
        username: this.id,
        password: this.password,
        name: this.name,
        //email: this.email
      };
      const response = await signUp(payload);
      // TODO:
      console.log(response);
    },
    onReset() {
      this.form.name = '';
      this.form.id = '';
      this.form.password = '';
      this.form.passwordCheck = '';
    },
  },
};
</script>
