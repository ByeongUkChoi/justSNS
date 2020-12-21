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

      <b-button type="submit" variant="primary">Submit</b-button>
      <b-button type="reset" variant="danger">Reset</b-button>
    </b-form>
  </b-container>
</template>

<script>
  export default {
    data() {
      return {
        text: '',
        show: true
      }
    },
    methods: {
      onSubmit() {
          // TODO:
      },
      onReset() {
        // Reset our form values
        this.text = ''
        // Trick to reset/clear native browser form validation state
        this.show = false
        this.$nextTick(() => {
          this.show = true
        })
      }
    }
  }
</script>