module.exports = {
  "transpileDependencies": [
    "vuetify"
  ],
  // 개발 서버 설정
  devServer: {
    // 프록시 설정
    proxy: {
        // 프록시 요청을 보낼 api의 시작 부분
        '/mindwiki/mind/update': {
            // 프록시 요청을 보낼 서버의 주소
            target: 'http://localhost:8000',
            changeOrigin: true,
        },
        '/mindwiki/mind/comment/make': {
            // 프록시 요청을 보낼 서버의 주소
            target: 'http://localhost:8000',
            changeOrigin: true,
        },
        '/mindwiki/mind/comment/delete': {
            // 프록시 요청을 보낼 서버의 주소
            target: 'http://localhost:8000',
            changeOrigin: true,
        },
        '/mindwiki/liketag/update': {
            // 프록시 요청을 보낼 서버의 주소
            target: 'http://localhost:8000',
            changeOrigin: true,
        },
        '/mindwiki/profile/memberList' : {
            // 프록시 요청을 보낼 서버의 주소
            target: 'http://localhost:8000',
            changeOrigin: true,      
        },
        '/mindwiki/node/getNode' : {
            // 프록시 요청을 보낼 서버의 주소
            target: 'http://localhost:8000',
            changeOrigin: true,      
        },
        '/mindwiki/mind/like' : {
            // 프록시 요청을 보낼 서버의 주소
            target: 'http://localhost:8000',
            changeOrigin: true,      
        },
        '/mindwiki/mind/scrap' : {
            // 프록시 요청을 보낼 서버의 주소
            target: 'http://localhost:8000',
            changeOrigin: true,      
        }
    }
  }
};