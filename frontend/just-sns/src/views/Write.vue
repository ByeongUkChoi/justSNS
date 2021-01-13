<template>
  <b-container>
    <b-form @submit.prevent="onSubmit" @reset="onReset" v-if="show">
      <b-form-group
        label="Post a new post"
        description="Please post a new post."
      >
        <b-form-textarea
          id="textarea"
          v-model="text"
          placeholder="Enter something..."
          rows="3"
          max-rows="6"
          required
        ></b-form-textarea>
      </b-form-group>

      <b-button pill type="submit" variant="outline-primary">Submit</b-button
      >&nbsp;
      <b-button pill type="reset" variant="outline-danger">Reset</b-button>
    </b-form>
  </b-container>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      text: '',
      show: true,
    };
  },
  methods: {
    onSubmit() {
      axios
        .post('https://jsonplaceholder.typicode.com/posts', {
          content: this.text,
        })
        .then(() => {
          alert('Posting is complete');
          this.$router.push('/');
        })
        .catch(error => console.log(error));
    },
    onReset() {
      // Reset our form values
      this.text = '';
      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
  },
};
</script>
