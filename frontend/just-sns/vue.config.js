module.exports = {
  // 개발 시 화면에서 에러가 오버레이 되지 않도록 함
  devServer: {
    overlay: false,
    port: process.env.VUE_APP_PORT || 3000,
  },
};
