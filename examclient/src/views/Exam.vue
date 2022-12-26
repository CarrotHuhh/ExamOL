<template>
	<div class="wrapper">
		<div class="header-box">
			<div class="header-top">
				<p>考试中</p>
			</div>
			
			<div class="header-bottom">
				<div class="name-1">
					<div class="name-1-top">
						<p>{{paper.paperName}}</p>
						<div class="name-1-top-right">
							<p>满分</p>
							<p>{{exam.score}}</p>
						</div>
					</div>
					<div class="name-1-bottom">
						<p>{{exam.examDate}}</p>
						<div class="name-1-bottom-right">
							<p>考试时长</p>
							<p>{{exam.examTime}}min</p>
						</div>
					</div>
				</div>
				
				<div class="name-2">
					<div class="name-2-top">
						<p>已开始答题</p>
						<div class="time-1">
							<p v-show="minutes > 0">{{minutes}}min</p>
							<p>&ensp;{{seconds}}s</p>
						</div>
					</div>
					<div class="name-2-bottom">
						<p>距离考试结束还有</p>
						<div class="time-2">
							<p v-show="leftMinutes > 0">{{leftMinutes}}min</p>
							<p v-show="leftMinutes < exam.examTime && leftSecondes != 0" :class="{'ending': leftMinutes == 0}">&ensp;{{leftSecondes}}s</p>
						</div>
						
						<div class="time-3" v-show="(leftMinutes + leftSecondes) == 0">
							考试已结束
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="questionList-box">
			<div class="choose-question" v-for="item in choiceArr">
				<div class="question-box">
					{{item.No}}. {{item.content}}
				</div>
				<ul class="answer-box">
					<li>
						<div class="choose" @click="selectA(item)" :class="{'selected':item.isA, 'notSelected':!item.isA}" >A</div>
						<p>{{item.optionA}}</p>
					</li>
	
					<li>
						<div class="choose" @click="selectB(item)" :class="{'selected':item.isB, 'notSelected':!item.isB}">B</div>
						<p>{{item.optionB}}</p>
					</li>
					
					<li>
						<div class="choose" @click="selectC(item)" :class="{'selected':item.isC, 'notSelected':!item.isC}">C</div>
						<p>{{item.optionC}}</p>
					</li>
					
					<li>
						<div class="choose" @click="selectD(item)" :class="{'selected':item.isD, 'notSelected':!item.isD}">D</div>
						<p>{{item.optionD}}</p>
					</li>
				</ul>
			</div>
			
			<div class="fillIn-question" v-for="item in fillingArr">
				<div class="question-box">
					{{item.No}}. {{item.content}}
				</div>
				
				<div class="content-box">
					<textarea class="fillIn" v-model="item.answer"></textarea>
				</div>
			</div>
		</div>
		
		<div class="button-box">
			<div class="button-cancel" :class="{'examEnding':(leftMinutes + leftSecondes) == 0}" @click="toIndex">
				退出考试
			</div>
			
			<div class="button-submit" v-show="(leftMinutes + leftSecondes) > 0" @click="toSubmit">
				提交试卷
			</div>
		</div>
	</div>
</template>

<script>
	export default{
		name: 'Exam',
		data(){
			return{
				examId: this.$route.query.examId,
				paperId: this.$route.query.paperId,
				exam:{},
				paper:{},
				choiceArr:[],
				fillingArr:[],
				user:{},
				number:1,
				Submissions:[],
				timer:'',
				seconds:0,
				minutes:0,
				leftSecondes:60,
				leftMinutes:0
			}
		},
		created(){
			this.user = this.$getSessionStorage('user');
			
			this.$axios.post('ExamController/getExamByExamId', this.$qs.stringify({
				examId: this.examId
			})).then(response=>{
				this.exam = response.data;
				this.leftMinutes = this.exam.examTime;
				// this.leftMinutes--;
			}).catch(error=>{
				console.error(error);
			});
			
			this.$axios.post('PaperController/getPaperInfoById', this.$qs.stringify({
				paperId: this.paperId
			})).then(response=>{
				this.paper = response.data;
				this.$set(this.exam,'score', this.exam.choiceScore * this.paper.choiceAmount + this.exam.fillingScore * this.paper.fillingAmount);
			}).catch(error=>{
				console.error(error);
			});
			
			
			
			this.$axios.post('PaperController/getPaperChoice', this.$qs.stringify({
				paperId: this.paperId
			})).then(response=>{
				this.choiceArr = response.data;
				for(let item of this.choiceArr){
					// item.isA = false;
					// item.isB = false;
					// item.isC = false;
					// item.isD = false;
					this.$set(item, 'isA', false);
					this.$set(item, 'isB', false);
					this.$set(item, 'isC', false);
					this.$set(item, 'isD', false);
					this.$set(item, 'select', false);
					
					this.$set(item,'No', this.number);
					this.number = this.number + 1;
				}
			}).catch(error=>{
				console.error(error);
			});
			
			this.$axios.post('PaperController/getPaperFilling', this.$qs.stringify({
				paperId: this.paperId
			})).then(response=>{
				this.fillingArr = response.data;
				for(let item of this.fillingArr){
					item.answer = '';
					this.$set(item,'No', this.number);
					this.number = this.number + 1;
				}
			}).catch(error=>{
				console.error(error);
			});
			
			this.start();
		},
		mounted() {
			var textareaArr = document.getElementsByName("fillIn");
			
			console.log(textareaArr.length);
			
			for (let i = 0; i < textareaArr.length; i++){
				textareaArr[i].addEventListener('input', (e)=>{
					// textarea.style.height = e.target.scrollHeight > 100 ? e.target.scrollHeight + 'px' : '100px';
					textareaArr[i].style.height = '100px';
					textareaArr[i].style.height = e.target.scrollHeight + 'px';
				});
			}
		},
		methods:{
			toIndex(){
				var result = confirm("您确认退出考试吗？");
				
				if (result){
					this.$router.push({path:'/index'});
				}
			},
			selectA(item){
				if (!item.select || item.select && !item.isA){
					this.$set(item, 'isA', true);
					this.$set(item, 'isB', false);
					this.$set(item, 'isC', false);
					this.$set(item, 'isD', false);
					
					item.answer = item.optionA;
					this.$set(item, 'select', true);
				}
				else if (item.select && item.isA){
					this.$set(item, 'isA', false);
					this.$set(item, 'isB', false);
					this.$set(item, 'isC', false);
					this.$set(item, 'isD', false);
					
					item.answer = '';
					this.$set(item, 'select', false);
				}
				
				if (item.isA){
					console.log('A');
				}
			},
			selectB(item){
				if (!item.select || item.select && !item.isB){
					this.$set(item, 'isA', false);
					this.$set(item, 'isB', true);
					this.$set(item, 'isC', false);
					this.$set(item, 'isD', false);
					
					item.answer = item.optionB;
					this.$set(item, 'select', true);
				}
				else if (item.select && item.isB){
					this.$set(item, 'isA', false);
					this.$set(item, 'isB', false);
					this.$set(item, 'isC', false);
					this.$set(item, 'isD', false);
					
					item.answer = '';
					this.$set(item, 'select', false);
				}
				
				if (item.isB){
					console.log('B');
				}
			},
			selectC(item){
				if (!item.select || item.select && !item.isC){
					this.$set(item, 'isA', false);
					this.$set(item, 'isB', false);
					this.$set(item, 'isC', true);
					this.$set(item, 'isD', false);
					
					item.answer = item.optionC;
					this.$set(item, 'select', true);
				}
				else  if (item.select && item.isC){
					this.$set(item, 'isA', false);
					this.$set(item, 'isB', false);
					this.$set(item, 'isC', false);
					this.$set(item, 'isD', false);
					
					item.answer = '';
					this.$set(item, 'select', false);
				}
				
				if (item.isC){
					console.log('C');
				}
			},
			selectD(item){
				if (!item.select || item.select && !item.isD){
					this.$set(item, 'isA', false);
					this.$set(item, 'isB', false);
					this.$set(item, 'isC', false);
					this.$set(item, 'isD', true);
					
					item.answer = item.optionD;
					this.$set(item, 'select', true);
				}
				else if (item.select && item.isD){
					this.$set(item, 'isA', false);
					this.$set(item, 'isB', false);
					this.$set(item, 'isC', false);
					this.$set(item, 'isD', false);
					
					item.answer = '';
					this.$set(item, 'select', false);
				}
				
				if (item.isD){
					console.log(item.answer);
				}
			},
			toSubmit(){
				var result = confirm("您确认提交试卷吗？");
				
				if (result){
					for (let item of this.choiceArr){
						var Submission = {};
						Submission.questionId = item.questionId;
						Submission.examId = this.examId;
						Submission.answer = item.answer;
						Submission.type = item.type;
						
						console.log(Submission);					
						this.Submissions.push(Submission);
					}
					
					for (let item of this.fillingArr){
						var Submission = {};
						Submission.questionId = item.questionId;
						Submission.examId = this.examId;
						Submission.answer = item.answer;
						Submission.type = item.type;
						
						console.log(Submission);
						this.Submissions.push(Submission);
					}
					
					this.$axios.post('SubmissionController/submit', {
						submissions: this.Submissions
					}).then(response=>{
						var rows = response.data;
						if (rows > 0){
							alert('提交成功！');
							
							this.$axios.post('SubmissionController/grade', this.$qs.stringify({
								examId:this.examId
							})).then(response=>{
								var getGrade = response.data;
								
								if (getGrade >= 0){
									console.log('打分成功');
								}
								else{
									console.log('打分失败');
								}
							}).catch(error=>{
								console.error(error);
							});
							
							this.$axios.post('ExamController/updateExamState', this.$qs.stringify({
								examId:this.examId
							})).then(response=>{
								var getState = response.data;
								
								if (getState > 0){
									console.log('修改考试状态成功');
								}
								else{
									console.log('修改考试状态失败');
								}
							}).catch(error=>{
								console.error(error);
							});
							
							this.$axios.post('RecordController/setTime', this.$qs.stringify({
								min:this.minutes,
								sec:this.seconds,
								examId:this.examId
							})).then(response=>{
								var result = response.data;
								
								if (result > 0){
									console.log('添加时间成功');
								}
								else{
									console.log('添加时间失败');
								}
							}).catch(error=>{
								console.error(error);
							});
							
							this.$router.push({path:'/index'});
						}
						else{
							alert('提交失败！');
						}
					}).catch(error=>{
						console.error(error);
					});
					
					this.Submissions = [];
				}
			},
			start(){
				this.timer = setInterval(this.vauleAdd, 1000);
			},
			vauleAdd(){
				if (this.leftMinutes == this.exam.examTime){
					this.leftMinutes--;
				}
				
				this.seconds++;
				if (this.seconds == 60){
					this.minutes++;
					this.seconds = 0;
				}
				
				this.leftSecondes--;
				if (this.leftSecondes == -1){
					this.leftMinutes--;
					this.leftSecondes = 59;
				}
				
				if (this.minutes == this.exam.examTime){
					this.stop();
				}
			},
			stop(){
				clearInterval(this.timer);
				this.leftSecondes = 0;
			}
		},
		beforeDestroy() {
			this.seconds = 0;
			this.timer = 0;
			clearInterval(this.timer);
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
		color: #fff;
	}
	
	.wrapper .header-box .header-top{
		padding: 0vh 0vh 2vh 0vh;
		display: flex;
		justify-content: center;
		align-items: center;
		font-size: 10vw;
		font-weight: bold;
		letter-spacing: 3vw;
	}
	
	.wrapper .header-box .header-bottom{
		padding: 0vw 5vw 0vw 5vw;
		font-size: 3.5vw;
	}
	
	.wrapper .header-box .header-bottom .name-1{
		margin-bottom: 2vw;
	}
	
	.wrapper .header-box .header-bottom .name-1 .name-1-top{
		padding-bottom: 2vw;
		border-bottom: soli 1px #fff;
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
	
	.wrapper .header-box .header-bottom .name-1 .name-1-top p{
		font-weight: bold;
	}
	
	.wrapper .header-box .header-bottom .name-1 .name-1-top .name-1-top-right{
		display: flex;
		justify-content: center;
		align-items: center;
	}
	
	.wrapper .header-box .header-bottom .name-1 .name-1-bottom{
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
	
	.wrapper .header-box .header-bottom .name-1 .name-1-bottom p{
		font-weight: bold;
	}
	
	.wrapper .header-box .header-bottom .name-1 .name-1-bottom .name-1-bottom-right{
		display: flex;
		justify-content: center;
		align-items: center;
	}
	
	.wrapper .header-box .header-bottom .name-1 p:last-child{
		font-weight: bold;
		font-style: italic;
	}
	
	.wrapper .header-box .header-bottom .name-2{
		margin-top: 4vw;
		padding-top: 4vw;
		border-top: solid 1px #fff;
	}
	
	.wrapper .header-box .header-bottom .name-2 .time-1{
		display: flex;
		font-weight: bold;
		font-style: italic;
	}
	
	.wrapper .header-box .header-bottom .name-2 .time-2{
		display: flex;
		font-weight: bold;
		font-style: italic;
	}
	
	.wrapper .header-box .header-bottom .name-2 .time-2 .ending{
		display: flex;
		font-weight: bold;
		font-style: italic;
		color: red;
	}
	
	.wrapper .header-box .header-bottom .name-2 .time-3{
		color: red;
		font-weight: bold;
		font-style: italic;
	}
	
	.wrapper .header-box .header-bottom .name-2 .name-2-top{
		padding-bottom: 2vw;
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
	
	.wrapper .header-box .header-bottom .name-2 .name-2-bottom{
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
	
	/******************************** question list *****************************************/
	.wrapper .questionList-box{
		min-height: 50vh;
		border-radius: 8vw 8vw 0vw 0vw;
		padding: 10vw 8vw 10.7vh 8vw;
		background-color: #f2f2f6;
		color: #000;
		font-size: 5vw;
	}
	
	.wrapper .questionList-box .choose-question{
		padding-bottom: 4vw;
		margin-bottom: 5vw;
		border-bottom: solid 2px #565656;
	}
	
	.wrapper .questionList-box .choose-question .question-box{
		font-weight: bold;
	}
	
	.wrapper .questionList-box .choose-question .answer-box{
		margin-top: 2vw;
	}
	
	.wrapper .questionList-box .choose-question .answer-box li{
		margin-bottom: 1vw;
		display: flex;
		align-items: center;
		font-size: 4.5vw;
	}
	
	.wrapper .questionList-box .choose-question .answer-box li .choose{
		width: 5vw;
		height: 5vw;
		border-radius: 5vw;
		border: solid 3px #565656;
		color: #565656;
		display: flex;
		justify-content: center;
		align-items: center;
		font-size: 4vw;
		font-weight: bold;
		cursor: pointer;
	}
	
	.wrapper .questionList-box .choose-question .answer-box li .choose.selected{
		background-color: #005187;
		border: solid 3px #005187;
		color: #fff;
	}
	
	.wrapper .questionList-box .choose-question .answer-box li .choose.notSelected{
		background-color: none;
		border: solid 3px #565656;
		color: #565656;
	}
	
	.wrapper .questionList-box .choose-question .answer-box li p{
		margin-left: 5vw;
	}
	
	.wrapper .questionList-box .fillIn-question{
		padding-bottom: 5vw;
		margin-bottom: 5vw;
		border-bottom: solid 2px #565656;
	}
	
	.wrapper .questionList-box .fillIn-question .question-box{
		font-weight: bold;
	}
	
	.wrapper .questionList-box .fillIn-question .content-box{
		margin-top: 2vw;
	}
	
	.wrapper .questionList-box .fillIn-question .content-box textarea{
		/* cols: 20;
		rows: 10; */
		width: 84vw;
		min-height: 100px;
		margin-top: 2vw;
		outline-style: none;
		border: 0px;
		border-radius: 2vw;
		font-size: 4.5vw;
		font-family: "微软雅黑";
		text-indent: 2px;
		resize: none;
	}
	
	/******************************** button *****************************************/
	.wrapper .button-box{
		width: 84vw;
		margin: 0vw 8vw 8vw 8vw;
		display: flex;
		justify-content: space-between;
		position: fixed;
		left: 0;
		bottom: 0;
		color: #fff;
		font-size: 8vw;
		font-weight: bold;
	}
	
	.wrapper .button-box .button-cancel{
		width: 36vw;
		height: 12vw;
		display: flex;
		justify-content: center;
		align-items: center;
		border-radius: 1vw;
		background-color: darkgrey;
		font-size: 5vw;
		letter-spacing: 0vw;
		cursor: pointer;
	}
	
	.wrapper .button-box .button-cancel .examEnding{
		width: 84vw;
	}
	
	.wrapper .button-box .button-submit{
		width: 36vw;
		height: 12vw;
		display: flex;
		justify-content: center;
		align-items: center;
		border-radius: 1vw;
		background-color: limegreen;
		font-size: 5vw;
		letter-spacing: 0vw;
		cursor: pointer;
	}
</style>
