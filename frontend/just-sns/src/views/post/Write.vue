<template>
  <PostAddForm></PostAddForm>
</template>

<script>
import PostAddForm from '@/components/post/PostAddForm';
import axios from 'axios';

export default {
  components: {
    PostAddForm,
  },
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
