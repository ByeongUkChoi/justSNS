<template>
  <Editor :options="editorOptions" :initialEditType="initialEditType"></Editor>
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
};
</script>

<style></style>
