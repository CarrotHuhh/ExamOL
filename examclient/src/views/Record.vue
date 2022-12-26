<template>
	<div class="wrapper">
		<div class="header-box">
			<i class="fa fa-chevron-left" @click="toMyIndex"></i>
			<p>考试记录</p>
			<i class="fa fa-chevron-left"></i>
		</div>
		
		<ul class="examList-box">
			<li v-for="item in recordArr">
				<div class="left-box">
					<div class="left-top">
						<p>{{item.examName}}</p>
					</div>
					
					<div class="left-bottom">
						<p>{{item.examDate}}</p>
					</div>
				</div>
				
				<div class="right-box">
					<div class="right-top">
						<p>成绩</p>
						<p>{{item.score}}</p>
					</div>
					
					<div class="right-bottom">
						<p>用时&ensp;</p>
						<div class="right-bottom-right">
							<p v-show="item.min > 0">{{item.min}}min</p>
							<p v-show="item.sec != 0">{{item.sec}}sec</p>
						</div>
					</div>
				</div>
			</li>
		</ul>
	</div>
</template>

<script>
	export default{
		name:'Record',
		data(){
			return{
				user:{},
				recordArr:[]
			}
		},
		created() {
			this.user = this.$getSessionStorage('user');
			
			this.$axios.post('RecordController/getRecord', this.$qs.stringify({
				userId: this.user.userid
			})).then(response=>{
				this.recordArr = response.data;
				
				for (let item of this.recordArr){
					this.$axios.post('ExamController/getExamByExamId', this.$qs.stringify({
						examId: item.examId
					})).then(response=>{
						var exam = response.data;
						this.$set(item, 'examDate', exam.examDate);
					}).catch(error=>{
						console.error(error);
					});
				}
			}).catch(error=>{
				console.error(error);
			});
		},
		methods:{
			toMyIndex(){
				this.$router.push({path:'/myIndex'});
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
	
	
	/******************************** exam list *****************************************/
	.wrapper .examList-box{
		min-height: 50vh;
		border-radius: 8vw 8vw 0vw 0vw;
		padding: 10vw 8vw 28.3vh 8vw;
		background-color: #f2f2f6;
	}
	
	.wrapper .examList-box li{
		width: 84vw;
		height: 20vw;
		box-sizing: border-box;
		display: flex;
		justify-content: space-between;
		padding: 4vw;
		margin-bottom: 4vw;
		border-radius: 4vw;
		background-color: #565656;
		color: #fff;
		font-size: 3vw;
		font-weight: bold;
		cursor: pointer;
	}
	
	.wrapper .examList-box li .left-box{
		display: flex;
		flex-direction: column;
		justify-content: space-between;
	}
	
	.wrapper .examList-box li .left-box .left-top{
		font-size: 4vw;
	}
	
	.wrapper .examList-box li .right-box{
		display: flex;
		flex-direction: column;
		justify-content: space-between;
	}
	
	.wrapper .examList-box li .right-box .right-top{
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: flex-start;
	}
	
	.wrapper .examList-box li .right-box .right-top p:first-child{
		font-size: 4vw;
	}
	
	.wrapper .examList-box li .right-box .right-top p:last-child{
		display: flex;
		align-items: flex-start;
		font-size: 5vw;
		font-style: italic;
		color: red;
	}
	
	.wrapper .examList-box li .right-box .right-bottom{
		display: flex;
		flex-direction: row;
		justify-content: flex-end;
		align-items: flex-end;
	}
	
	.wrapper .examList-box li .right-box .right-bottom p:first-child{
		font-size: 3vw;
	}
	
	.wrapper .examList-box li .right-box .right-bottom .right-bottom-right{
		display: flex;
		flex-direction: row;
		justify-content: flex-end;
		align-items: flex-end;
		font-size: 4vw;
		font-style: italic;
	}
	
	.wrapper .examList-box li .right-box .right-bottom .right-bottom-right p{
		font-size: 4vw;
		font-style: italic;
	}
	
</style>