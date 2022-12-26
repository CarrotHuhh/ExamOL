<template>
	<div class="wrapper">
		<div class="user-box">
			<div class="userImg">
				<img src="../assets/defaultUserImg.jpg">
			</div>
			
			<div class="userInfo">
				<div class="name">
					<p>{{user.username}}同学</p>
				</div>
				<div class="UID">
					<p>UID</p>
					<p>{{user.userid}}</p>
				</div>
			</div>
		</div>
		
		<ul class="operation-box">
			<li @click="toRecord">
				<i class="fa fa-check-square-o"></i>
				<p>查询考试记录</p>
			</li>
			
			<li @click="toEdit">
				<i class="fa fa-cog"></i>
				<p>修改个人信息</p>
			</li>
		</ul>
		
		<div class="bottom-box">
			<div class="bottom-index" @click="toIndex">
				<i class="fa fa-list-alt"></i>
				<p>首页</p>
			</div>
			
			<div class="bottom-myIndex" @click="toMyIndex">
				<i class="fa fa-user-o"></i>
				<p>个人页面</p>
			</div>
		</div>
	</div>
</template>

<script>
	export default{
		name:'MyIndex',
		data() {
			return{
				user:{}
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
			toIndex(){
				this.$router.push({path:'/index'});
			},
			toMyIndex(){
				this.$router.push({path:'/myIndex'});
			},
			toRecord(){
				this.$router.push({path:'/record'});
			},
			toEdit(){
				this.$router.push({path:'/edit'});
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
	
	
	/******************************** user box *****************************************/
	.wrapper .user-box{
		width: 100%;
		box-sizing: border-box;
		padding: 5vh 8vw 5vh 8vw;
		display: flex;
		color: #fff;
	}
	
	.wrapper .user-box .userImg img{
		width: 10vh;
		height: 10vh;
		border-radius: 10vh;
	}
	
	.wrapper .user-box .userInfo{
		width: 40vw;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: flex-start;
	}
	
	.wrapper .user-box .userInfo .name{
		padding-bottom: 1vw;
		border-bottom: solid 1px #fff;
		font-size: 6vw;
		font-weight: bold;
		margin: 0vh 0vw 0vw 7vw;
	}
	
	.wrapper .user-box .userInfo .UID{
		width: 20vw;
		margin: 0vw 0vw 0vw 7vw;
		padding-top: 1vw;
		/* border-top: solid 1px #fff; */
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		font-size: 4vw;
		font-weight: 100;
	}
	
	.wrapper .user-box .userInfo .UID p:first-child{
		font-weight: bold;
		font-style: italic;
	}
	
	
	/******************************** exam list *****************************************/
	.wrapper .operation-box{
		border-radius: 8vw 8vw 0vw 0vw;
		padding: 10vw 8vw 60vh 8vw;
		background-color: #f2f2f6;
	}
	
	.wrapper .operation-box li{
		width: 84vw;
		height: 16vw;
		box-sizing: border-box;
		display: flex;
		align-items: center;
		padding: 4vw;
		margin-bottom: 4vw;
		border-radius: 4vw;
		background-color: darkgray;
		color: #fff;
		font-size: 4.5vw;
		font-weight: bold;
		cursor: pointer;
	}
	
	.wrapper .operation-box li p{
		margin-left: 3vw;
	}
	
	/*********************************** bottom ***********************************/
	.wrapper .bottom-box{
		width: 100%;
		height: 16vw;
		border-top: solid 1px #b2b2b2;
		background: rgba(247, 247, 247, 0.6);
		backdrop-filter: blur(40vw);
		
		position: fixed;
		left: 0;
		bottom: 0;
		
		display: flex;
		justify-content: space-around;
		align-items: center;
	}
	
	.wrapper .bottom-box .bottom-index{
		font-size: 2.8vw;
		font-weight: bold;
		color: #959595;
		flex: 1;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		user-select: none;
		cursor: pointer;
	}
	
	.wrapper .bottom-box .bottom-myIndex{
		font-size: 2.8vw;
		font-weight: bold;
		color: #3478f6;
		flex: 1;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		user-select: none;
		cursor: pointer;
	}
	
	.wrapper .bottom-box i{
		font-size: 5vw;
		margin-bottom: 1.2vw;
	}
</style>