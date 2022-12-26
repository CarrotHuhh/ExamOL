<template>
	<div class="wrapper">
		<div class="header-box">
			<i class="fa fa-chevron-left" @click="toMyIndex"></i>
			<p>修改信息</p>
			<i class="fa fa-chevron-left"></i>
		</div>
		
		<ul class="operation-box">
			<li>
				<div class="operation-header" @click="toEditName" v-show="!editNameSelect">
					<p>修改姓名</p>
					<i class="fa fa-caret-right"></i>
				</div>
				
				<div class="content-box" v-show="editNameSelect">
					<p>修改姓名：</p>
					<input type="text" v-model="newName" placeholder="请输入修改后的姓名">
				</div>
				
				<div class="bottom-box" v-show="editNameSelect">
					<div class="button-box" @click="editName">
						确认提交
					</div>
					<i class="fa fa-caret-up" @click="toEditName"></i>
				</div>
			</li>
			
			<li>
				<div class="operation-header" @click="toEditAccount" v-show="!editAccountSelect">
					<p>修改账号</p>
					<i class="fa fa-caret-right"></i>
				</div>
				
				<div class="content-box" v-show="editAccountSelect">
					<p>修改账号：</p>
					<input type="text" @blur="confirmAccount" v-model="newAccount" placeholder="请输入修改后的账号">
				</div>
				
				<div class="content-box" v-show="editAccountSelect">
					<p>请输入密码进行验证：</p>
					<input type="password" v-model="password" placeholder="请输入密码">
				</div>
				
				<div class="bottom-box" v-show="editAccountSelect">
					<div class="button-box" @click="editAccount">
						确认提交
					</div>
					<i class="fa fa-caret-up" @click="toEditAccount"></i>
				</div>
			</li>
			
			<li>
				<div class="operation-header" @click="toEditPassword" v-show="!editPasswordSelect">
					<p>修改密码</p>
					<i class="fa fa-caret-right"></i>
				</div>
				
				<div class="content-box" v-show="editPasswordSelect">
					<p>原密码：</p>
					<input type="password" v-model="oldPassword" placeholder="请输入原密码">
				</div>
				
				<div class="content-box" v-show="editPasswordSelect">
					<p>新密码：</p>
					<input type="password" v-model="newPassword" placeholder="请输入修改后的密码">
				</div>
				
				<div class="content-box" v-show="editPasswordSelect">
					<p>确认新密码：</p>
					<input type="password" v-model="confirmNewPassword" placeholder="请再次输入修改后的密码进行确认">
				</div>
				
				<div class="bottom-box" v-show="editPasswordSelect">
					<div class="button-box" @click="editPassword">
						确认提交
					</div>
					<i class="fa fa-caret-up" @click="toEditPassword"></i>
				</div>
			</li>
		</ul>
	</div>
</template>

<script>
	export default{
		name:'Edit',
		data() {
			return{
				user:{},
				newUser:{},
				newName:'',
				newAccount:'',
				password:'',
				oldPassword:'',
				newPassword:'',
				confirmNewPassword:'',
				editNameSelect:false,
				editAccountSelect:false,
				editPasswordSelect:false
			}
		},
		created() {
			this.user = this.$getSessionStorage('user');
		},
		watch:{
			$route(){
				if (this.$router.history.current.name == 'MyIndex'){
					this.$router.go();
				}
			}
		},
		methods:{
			toMyIndex(){
				this.$router.push({path:'/myIndex'});
			},
			toEditName(){
				this.editNameSelect = !this.editNameSelect;
			},
			toEditAccount(){
				this.editAccountSelect = !this.editAccountSelect;
			},
			toEditPassword(){
				this.editPasswordSelect = !this.editPasswordSelect;
			},
			editName(){
				if (this.newName == ''){
					alert('请输入修改后的姓名！');
					return;
				}
				
				this.$axios.post('UserController/updateusername', this.$qs.stringify({
					useraccount: this.user.useraccount,
					username: this.newName
				})).then(response=>{
					var result = response.data;
					
					if (result > 0){
						alert('用户姓名修改成功！')
						console.log('姓名修改成功');
						
						this.$axios.post('UserController/getUser', this.$qs.stringify({
							useraccount: this.user.useraccount,
							password: this.user.password
						})).then(response=>{
							let user = response.data;
							this.$setSessionStorage('user', user);
						}).catch(error=>{
							console.error(error);
						});
						this.toMyIndex();
					}
					else{
						console.log('姓名修改失败');
					}
				}).catch(error=>{
					console.error(error);
				});
			},
			confirmAccount(){
				this.$axios.post('UserController/getUserByaccount', this.$qs.stringify({
					useraccount: this.newAccount,
				})).then(response=>{
					var result = response.data;
					
					if (result > 0){
						alert('此账号已存在！')
						console.log('新账号已存在');
						return;
					}
					else{
						console.log('新账号不存在，可修改');
					}
				}).catch(error=>{
					console.error(error);
				});
			},
			editAccount(){
				if (this.newAccount == ''){
					alert('请输入修改后的账号！');
					return;
				}
				if (this.password == null){
					alert('请输入密码进行验证！');
					return;
				}
				
				this.$axios.post('UserController/getUserByaccount', this.$qs.stringify({
					useraccount: this.newAccount,
				})).then(response=>{
					var result = response.data;
					
					if (result > 0){
						alert('此账号已存在！')
						console.log('新账号已存在');
						return;
					}
					else{
						console.log('新账号不存在，可修改');
						this.$axios.post('UserController/getUser', this.$qs.stringify({
							useraccount: this.user.useraccount,
							password: this.password
						})).then(response=>{
							var result = response.data;
							
							if (result != 0){
								console.log('密码正确');
								this.$axios.post('UserController/updateuseraccount', this.$qs.stringify({
									useraccount: this.user.useraccount,
									newaccount: this.newAccount
								})).then(response=>{
									var result = response.data;
									
									if (result > 0){
										alert('用户账号修改成功！')
										console.log('账号修改成功');
										
										this.$axios.post('UserController/getUser', this.$qs.stringify({
											useraccount: this.newAccount,
											password: this.user.password
										})).then(response=>{
											let user = response.data;
											this.$setSessionStorage('user', user);
										}).catch(error=>{
											console.error(error);
										});
										
										this.toMyIndex();
									}
									else{
										console.log('账号修改失败');
									}
								}).catch(error=>{
									console.error(error);
								});
							}
							else{
								alert('密码错误！修改账号失败！');
								console.log('密码错误');
							}
						}).catch(error=>{
							console.error(error);
						});
					}
				}).catch(error=>{
					console.error(error);
				});
			},
			editPassword(){
				if (this.oldPassword == ''){
					alert('请输入原密码！');
					return;
				}
				if (this.newPassword == ''){
					alert('请输入修改后的密码！');
					return;
				}
				if (this.confirmNewPassword == ''){
					alert('请再次输入修改后的密码进行验证！');
					return;
				}
				if (this.confirmNewPassword != this.newPassword){
					alert('两次输入的新密码不一致！');
					return;
				}
				
				console.log(this.user.useraccount);
				
				this.$axios.post('UserController/getUser', this.$qs.stringify({
					useraccount: this.user.useraccount,
					password: this.oldPassword
				})).then(response=>{
					var result = response.data;
					
					if (result != 0){
						console.log('密码正确');
						this.$axios.post('UserController/updatepassword', this.$qs.stringify({
							useraccount: this.user.useraccount,
							password: this.newPassword
						})).then(response=>{
							var result = response.data;
							
							if (result > 0){
								alert('用户密码修改成功！')
								console.log('密码修改成功');
								
								this.$axios.post('UserController/getUser', this.$qs.stringify({
									useraccount: this.user.useraccount,
									password: this.newPassword
								})).then(response=>{
									let user = response.data;
									this.$setSessionStorage('user', user);
								}).catch(error=>{
									console.error(error);
								});
								
								this.toMyIndex();
							}
							else{
								console.log('密码修改失败');
							}
						}).catch(error=>{
							console.error(error);
						});
					}
					else{
						alert('密码错误！修改密码失败！');
						console.log('密码错误');
					}
				}).catch(error=>{
					console.error(error);
				});
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
		padding: 5vh 0vh 5vh 0vh;
		display: flex;
		justify-content: space-between;
		align-items: center;
		color: #fff;
		font-size: 10vw;
		font-weight: bold;
		letter-spacing: 3vw;
	}
	
	.wrapper .header-box i{
		margin-top: 1.5vw;
	}
	
	.wrapper .header-box i:first-child{
		cursor: pointer;
	}
	
	.wrapper .header-box i:last-child{
		color: #005187;
	}
	
	
	/******************************** operation list *****************************************/
	.wrapper .operation-box{
		min-height: 70.21vh;
		border-radius: 8vw 8vw 0vw 0vw;
		padding: 10vw 8vw 8.1vh 8vw;
		background-color: #f2f2f6;
	}
	
	.wrapper .operation-box li{
		width: 84vw;
		box-sizing: border-box;
		display: flex;
		justify-content: space-between;
		flex-wrap: wrap;
		padding: 6vw 8vw 6vw 8vw;
		margin-bottom: 4vw;
		border-radius: 4vw;
		background-color: #565656;
		color: #fff;
		font-size: 5vw;
		font-weight: bold;
		cursor: pointer;
	}
	
	.wrapper .operation-box li .operation-header{
		display: flex;
		align-items: center;
	}
	
	.wrapper .operation-box li .operation-header i{
		margin: 0.55vw 0vw 0vw 2vw;
	}
	
	.wrapper .operation-box li .content-box{
		width: 65vw;
		height: 22vw;
		padding-bottom: 2vw;
	}
	
	.wrapper .operation-box li .content-box p{
		padding-bottom: 2.5vw;
	}
	
	.wrapper .operation-box li .content-box input{
		width: 64vw;
		height: 10vw;
		outline-style: none;
		border: 0px;
		border-radius: 2vw;
		font-size: 4vw;
		text-indent: 2px;
	}
	
	.wrapper .operation-box li .bottom-box{
		width: 65vw;
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
	
	.wrapper .operation-box li .bottom-box i{
		margin-top: 2vw;
		font-size: 8vw;
	}
	
	.wrapper .operation-box li .bottom-box .button-box{
		width: 25vw;
		height: 10vw;
		border-radius: 2vw;
		margin-top: 2vw;
		display: flex;
		justify-content: center;
		align-items: center;
		background-color: limegreen;
		cursor: pointer;
	}
</style>