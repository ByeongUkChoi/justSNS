<template>
  <b-form @submit="onSubmit" @reset="onReset">
    <b-form-group
      id="fieldset-1"
      label-cols-sm="4"
      label-cols-lg="3"
      content-cols-sm
      content-cols-lg="7"
      description=""
      label="이름"
      label-for="name"
      :invalid-feedback="`이름을 입력해주세요. (${this.minLength.name}글자 이상)`"
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
    <b-button type="submit" variant="primary">Submit</b-button>
    <b-button type="reset" variant="danger">Reset</b-button>
  </b-form>
</template>
<script>
import { duplicateCheck } from '@/api/auth';

export default {
  computed: {
    verifyName() {
      return this.checkLength('name');
    },
    verifyId() {
      if (!this.checkLength('id')) {
        return false;
      }
      if (!this.isDuplicateId()) {
        return false;
      }
      // TODO: id 중복 검사
      return true;
    },
    idInvalidFeedback() {
      if (!this.checkLength('id')) {
        return `아이디를 입력해 주세요. (${this.minLength.id}글자 이상)`;
      }
      // TODO: id 중복 검사
      if (this.isDuplicateId()) {
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
        return `비밀번호를 입력해 주세요. (${this.minLength.password}글자 이상)`;
      }
      // TODO: password 정합성 검사
      return '';
    },
    verifyPasswordCheck() {
      if (!this.checkLength('passwordCheck')) {
        return false;
      }
      if (!this.equalPasswordCheck()) {
        return false;
      }
      // TODO: password 정합성 검사
      return true;
    },
    passwordCheckInvalidFeedback() {
      if (!this.checkLength('passwordCheck')) {
        return `비밀번호를 입력해 주세요. (${this.minLength.password}글자 이상)`;
      }
      if (!this.equalPasswordCheck()) {
        return '비밀번호가 다릅니다.';
      }
      // TODO: password 정합성 검사
      return '';
    },
  },
  data() {
    return {
      minLength: {
        name: 2,
        id: 3,
        password: 4,
        passwordCheck: 4,
      },
      form: {
        name: '',
        id: '',
        password: '',
        passwordCheck: '',
      },
    };
  },
  methods: {
    checkLength(type) {
      return this.form[type].length >= this.minLength[type];
    },
    async isDuplicateId() {
      try {
        await duplicateCheck(this.form.id);
        return false;
      } catch (error) {
        return false;
      }
    },
    equalPasswordCheck() {
      return this.form.password === this.form.passwordCheck;
    },
    onSubmit() {
      // TODO:
      alert('submit');
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
