<template>
  <div>
    <Editor
      ref="toastuiEditor"
      :options="editorOptions"
      :initialEditType="initialEditType"
    ></Editor>
    <vs-button @click="submit">submit</vs-button>
  </div>
</template>

<script>
import 'codemirror/lib/codemirror.css';
import '@toast-ui/editor/dist/toastui-editor.css';

import { Editor } from '@toast-ui/vue-editor';

export default {
  components: {
    Editor,
  },
  data() {
    return {
      //   initialEditType: 'markdown',
      initialEditType: 'wysiwyg',
      editorOptions: {
        hideModeSwitch: true,
        toolbarItems: ['image'],
      },
    };
  },
  methods: {
    getHtml() {
      let html = this.$refs.toastuiEditor.invoke('getHtml');
      console.log(html);
    },
    submit() {
      this.getHtml();
    },
  },
};
</script>

<style></style>
