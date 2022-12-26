<template>
	<div class="wrapper">
		<div class="header-box">
			<p>考试科目</p>
		</div>
		
		<ul class="examList-box">
			<div class="empty" v-show="emptyShow">
				当前无考试
			</div>
			
			<li v-for="item in examArr" v-show="item.examState == 0" @click="toExam(item.paperId, item.examId)" :style="item.color">
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
						<p>满分</p>
						<p>{{item.score}}</p>
					</div>
					
					<div class="right-bottom">
						<p>考试时长</p>
						<p>{{item.examTime}}min</p>
					</div>
				</div>
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
		name:'Index',
		data(){
			return{
				user:{},
				examArr:[],
				color:['background-color: orange',
				'background-color: cadetblue',
				'background-color: coral',
				'background-color: powderblue',
				'background-color: olivedrab'],
				paper:{},
				emptyShow:true,
				reloadCnt:true
			}
		},
		created() {
			this.user = this.$getSessionStorage('user');
			
			this.$axios.post('ExamController/getExamById', this.$qs.stringify({
				userId:this.user.userid
			})).then(response=>{
				this.examArr = response.data;
				
				var cnt = 0;
				var i = 0;
				
				for (let item of this.examArr){
					item.color = this.color[i];
					i = i + 1;
					i = i % 5;
					this.paper = '';
					
					if (item.examState == 1){
						cnt = cnt + 1;
					}
					
					this.$axios.post('PaperController/getPaperInfoById', this.$qs.stringify({
						paperId:item.paperId
					})).then(response=>{
						this.paper = response.data;
						this.$set(item, 'score', item.choiceScore * this.paper.choiceAmount + item.fillingScore * this.paper.fillingAmount);
					}).catch(error=>{
						console.error(error);
					});
				}
				
				if (cnt < this.examArr.length){
					this.emptyShow = false;
				}
				
			}).catch(error=>{
				console.error(error);
			});
		},
		watch:{
			$route(){
				if (this.$router.history.current.name == 'Index'){
					this.$router.go();
				}
			}
		},
		methods:{
			toExam(paperId, examId){
				this.$axios.post('ExamController/getExamByExamId', this.$qs.stringify({
					examId: examId
				})).then(response=>{
					var examTemp = response.data;
					const tempDate = this.$moment().format(examTemp.examDate, 'yyyy-MM-dd hh:mm');
					const examDate = new Date(tempDate);
					
					const now = new Date();
					const tempNow = this.$moment(now, 'yyyy-MM-dd hh:mm').format();
					const nowDate = new Date(tempNow);
					
					// console.log(examTemp.examDate);
					console.log(examDate);
					console.log(nowDate);
					
					if (examDate < nowDate){
						this.$router.push({path:'/exam', query:{paperId:paperId, examId:examId}});
					}
					else{
						alert('考试时间未到！');
					}
				}).catch(error=>{
					console.error(error);
				});
				
				
			},
			toIndex(){
				this.$router.push({path:'/index'});
			},
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
		justify-content: center;
		align-items: center;
		color: #fff;
		font-size: 10vw;
		font-weight: bold;
		letter-spacing: 3vw;
	}
	
	
	/******************************** exam list *****************************************/
	.wrapper .examList-box{
		min-height: 25vh;
		border-radius: 8vw 8vw 0vw 0vw;
		padding: 10vw 8vw 50vh 8vw;
		background-color: #f2f2f6;
	}
	
	.wrapper .examList-box .empty{
		padding-top: 5vh;
		display: flex;
		justify-content: center;
		align-items: center;
		color: #b2b2b2;
		font-size: 6vw;
		font-weight: bold;
	}
	
	.wrapper .examList-box li{
		width: 84vw;
		height: 26vw;
		box-sizing: border-box;
		display: flex;
		justify-content: space-between;
		padding: 4vw;
		margin-bottom: 4vw;
		border-radius: 4vw;
		/* background-color: orange; */
		color: #fff;
		font-size: 4vw;
		font-weight: bold;
		cursor: pointer;
	}
	
	/* .wrapper .examList-box li:last-child{
		background-color: cadetblue;
	} */
	
	.wrapper .examList-box li .left-box{
		display: flex;
		flex-direction: column;
		justify-content: space-between;
	}
	
	.wrapper .examList-box li .left-box .left-top{
		font-size: 5vw;
	}
	
	.wrapper .examList-box li .right-box{
		display: flex;
		flex-direction: column;
		justify-content: space-between;
	}
	
	.wrapper .examList-box li .right-box .right-top{
		display: flex;
		flex-direction: row;
		justify-content: flex-end;
		align-items: flex-start;
	}
	
	.wrapper .examList-box li .right-box .right-top p:first-child{
		font-size: 3vw;
	}
	
	.wrapper .examList-box li .right-box .right-top p:last-child{
		display: flex;
		align-items: flex-start;
		font-size: 4vw;
		font-style: italic;
	}
	
	.wrapper .examList-box li .right-box .right-bottom{
		display: flex;
		flex-direction: column;
		justify-content: flex-end;
		align-items: flex-end;
	}
	
	.wrapper .examList-box li .right-box .right-bottom p:first-child{
		font-size: 3vw;
	}
	
	.wrapper .examList-box li .right-box .right-bottom p:last-child{
		font-size: 4vw;
		font-style: italic;
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
		color: #3478f6;
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
		color: #959595;
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