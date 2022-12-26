<template>
	<div class="wrapper">
		<div class="header-box">
			<p>注册</p>
		</div>
		
		<ul class="register-box">
			<li>
				<div class="content">
					<p>姓名：</p>
					<input type="text" v-model="user.username" placeholder="请输入您的姓名">
				</div>
			</li>
			
			<li>
				<div class="content">
					<p>账号：</p>
					<input type="text" @blur="checkUseraccount" v-model="user.useraccount" placeholder="请输入您的登录账号">
				</div>
			</li>
			
			<li>
				<div class="content">
					<p>密码：</p>
					<input type="password" v-model="user.password" placeholder="请输入您的密码">
				</div>
			</li>
			
			<li>
				<div class="content">
					<p>确认密码：</p>
					<input type="password" v-model="confirmpassword" placeholder="请再次输入您的密码">
				</div>
			</li>
		</ul>
		
		<div class="button-box" @click="register">
			<div class="button-finish">
				<p>完成</p>
			</div>
			
			<div class="button-cancel" @click="toLogin">
				<p>取消</p>
			</div>
		</div>
	</div>
</template>

<script>
	export default{
		name:'Register',
		data(){
			return{
				user:{
					password:'',
					username:'',
					useraccount:''
				},
				confirmpassword:''
			}
		},
		methods:{
			checkUseraccount(){
				this.$axios.post('UserController/getUserByaccount', this.$qs.stringify({
					useraccount: this.user.useraccount
				})).then(response=>{
					if (response.data == 1){
						this.user.useraccount = '';
						alert('此账号已存在！');
					}
				}).catch(error=>{
					console.error(error);
				});
			},
			register(){
				if (this.user.useraccount == ''){
					alert('账号不能为空!');
					return;
				}
				if (this.user.password == ''){
					alert('密码不能为空!');
					return;
				}
				if (this.user.password != this.confirmpassword){
					alert('两次输入的密码不一致!');
					return;
				}
				if (this.user.username == ''){
					alert('姓名不能为空!');
					return;
				}
				
				this.$axios.post('UserController/signIn', this.$qs.stringify(
					this.user
				)).then(response=>{
					if (response.data > 0){
						alert('注册成功！');
						this.$router.go(-1);
					}
					else{
						alert('注册失败！');
					}
				}).catch(error=>{
					console.error(error);
				});
			},
			toLogin(){
				this.$router.push({path:'/login'});
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
		padding-top: 8vh;
		display: flex;
		justify-content: center;
		align-items: center;
		color: #fff;
		font-size: 10vw;
		font-weight: bold;
		letter-spacing: 3vw;
	}
	
	
	/******************************** register *****************************************/
	.wrapper .register-box{
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
	
	.wrapper .register-box li{
		width: 65vw;
		padding: 0.5vh 0vw 1.2vh 0vw;
		font-size: 2.6vh;
		font-weight: bold;
		color: #fff;
	}
	
	.wrapper .register-box li .content{
		width: 65vw;
		height: 10vh;
	}
	
	.wrapper .register-box li .content p{
		padding-bottom: 1vh;
	}
	
	.wrapper .register-box li .content input{
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
	
	.wrapper .button-box .button-finish{
		width: 36vw;
		height: 6vh;
		border-radius: 1vh;
		display: flex;
		justify-content: center;
		align-items: center;
		background-color: orange;
		cursor: pointer;
	}
	
	.wrapper .button-box .button-cancel{
		width: 36vw;
		height: 6vh;
		border-radius: 1vh;
		display: flex;
		justify-content: center;
		align-items: center;
		background-color: darkgrey;
		cursor: pointer;
	}
</style>