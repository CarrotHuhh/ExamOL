<template>
	<div class="wrapper">
		<div class="header-box">
			<div class="name-1">
				智慧考试系统
			</div>
			
			<div class="name-2">
				（学生端）
			</div>
		</div>
		
		<ul class="login-box">
			<li>
				<div class="content">
					<p>账号：</p>
					<input type="text" v-model="useraccount">
				</div>
			</li>
			
			<li>
				<div class="content">
					<p>密码：</p>
					<input type="password" v-model="password">
				</div>
			</li>
			
			<li>
				请使用您的帐号以及密码进行登录，若无账号请进行注册
			</li>
		</ul>
		
		<div class="button-box" @click="login">
			<div class="button-login">
				<p>登录</p>
			</div>
			
			<div class="button-register" @click="toRegister">
				<p>注册</p>
			</div>
		</div>
		
	</div>
</template>

<script>
	export default{
		name: 'Login',
		data(){
			return{
				useraccount:'',
				password:''
			}
		},
		methods:{
			login(){
				if (this.useraccount == ''){
					alert("账号不能为空！");
					return;
				}
				if (this.password == ''){
					alert("密码不能为空！");
					return;
				}
				
				this.$axios.post('UserController/getUser', this.$qs.stringify({
					useraccount: this.useraccount,
					password: this.password
				})).then(response=>{
					let user = response.data;
					
					if (user == 0) {
						alert('账号或密码不正确！');
					}
					else{
						//avoid the data full over in the sessionStorage
						//user.userImg = '';
						this.$setSessionStorage('user', user);
						this.$router.push({path:'/index'});
					}
				}).catch(error=>{
					console.error(error);
				});
			},
			toRegister(){
				this.$router.push({path:'/register'});
			}
		}
	}
</script>

<style scoped>
	/* body{
		background-color: #005187;
	} */
	
	.wrapper{
		width: 100%;
		height: 100%;
		background-color: #005187;
		-webkit-user-select: none;
		user-select: none;
	}
	
	
	/******************************** header *****************************************/
	.wrapper .header-box{
		margin: 0vw 8vw 2vw 8vw;
		padding-top: 10vh;
		color: #fff;
	}
	
	.wrapper .header-box .name-1{
		margin-bottom: 2vw;
		display: flex;
		justify-content: center;
		align-items: center;
		font-size: 10vw;
		font-weight: bold;
		letter-spacing: 3vw;
	}
	
	.wrapper .header-box .name-2{
		display: flex;
		justify-content: center;
		align-items: center;
		font-size: 6vw;
	}
	
	
	/******************************** login *****************************************/
	.wrapper .login-box{
		margin: 5vh 10vw 0vw 10vw;
		padding: 2.5vh 0vw 2.5vh 0vw;
		box-sizing: border-box;
		border-radius: 1.5vh;
		display: flex;
		flex-wrap: wrap;
		justify-content: center;
		align-items: center;
		background-color: rgba(255, 255, 255, 0.1);
	}
	
	.wrapper .login-box li{
		width: 65vw;
		padding: 0.5vh 0vw 0.5vh 0vw;
		font-size: 2.6vh;
		font-weight: bold;
		color: #fff;
	}
	
	.wrapper .login-box li:last-child{
		width: 65vw;
		padding: 2.5vh 0vw 0.5vh 0vw;
		font-size: 1.5vh;
		font-weight: 100;
		color: #fff;
	}
	
	.wrapper .login-box li .content{
		width: 65vw;
		height: 10vh;
	}
	
	.wrapper .login-box li p{
		padding-bottom: 1vh;
	}
	
	.wrapper .login-box li .content input{
		width: 64vw;
		height: 5vh;
		outline-style: none;
		border: 0px;
		border-radius: 1vh;
		font-size: 2vh;
	}
	
	
	/******************************** button *****************************************/
	.wrapper .button-box{
		margin: 2vh 10vw 0vw 10vw;
		padding-bottom: 10vh;
		display: flex;
		justify-content: space-between;
		color: #fff;
		font-size: 2.6vh;
		font-weight: bold;
	}
	
	.wrapper .button-box .button-login{
		width: 36vw;
		height: 6vh;
		border-radius: 1vh;
		display: flex;
		justify-content: center;
		align-items: center;
		background-color: limegreen;
		cursor: pointer;
	}
	
	.wrapper .button-box .button-register{
		width: 36vw;
		height: 6vh;
		border-radius: 1vh;
		display: flex;
		justify-content: center;
		align-items: center;
		background-color: lightblue;
		cursor: pointer;
	}
</style>