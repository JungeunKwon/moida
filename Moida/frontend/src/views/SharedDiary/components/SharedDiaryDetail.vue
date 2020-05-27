<template>
	<v-dialog v-model="isOpen" width="500">
		<div id="roomDetail">
			<div id="detailLeft">
				<!-- <img id="detailLeft" :src="detail.imgUrl" /> -->
				<div class="container">
					<div class="main">
						<ul id="bk-list" class="bk-list clearfix">
							<li>
								<div class="bk-book book-1 bk-bookdefault">
									<div class="bk-front">
										<div class="bk-cover-back"></div>
										<div class="bk-cover">
											<h2>
												<span>Anthony Burghiss</span>
												<span>A Catwork Orange</span>
											</h2>
										</div>
									</div>
									<div class="bk-page">
										<div class="bk-content bk-content-current">
											<p>
												Red snapper Kafue pike fangtooth humums
												slipmouth, salmon cutlassfish; swallower
												European perch mola mola sunfish, threadfin
												bream. Billfish hog sucker trout-perch lenok
												orbicular velvetfish. Delta smelt striped
												bass, medusafish dragon goby starry flounder
												cuchia round whitefish northern anchovy
												spadefish merluccid hake cat shark Black
												pickerel. Pacific cod.
											</p>
										</div>
										<div class="bk-content">
											<p>
												Whale catfish leatherjacket deep sea
												anglerfish grenadier sawfish pompano
												dolphinfish carp large-eye bream, squeaker
												amago. Sandroller; rough scad, tiger
												shovelnose catfish snubnose parasitic eel?
												Black bass soldierfish duckbill--Rattail
												Atlantic saury Blind shark California
												halibut; false trevally warty angler!
											</p>
										</div>
										<div class="bk-content">
											<p>
												Trahira giant wels cutlassfish snapper koi
												blackchin mummichog mustard eel rock bass
												whiff murray cod. Bigmouth buffalo ling cod
												giant wels, sauger pink salmon. Clingfish
												luderick treefish flatfish Cherubfish
												oldwife Indian mul gizzard shad hagfish
												zebra danio. Butterfly ray lizardfish
												ponyfish muskellunge Long-finned sand diver
												mullet swordfish limia ghost carp filefish.
											</p>
										</div>
									</div>

									<div class="bk-right"></div>
									<div class="bk-left">
										<h2>
											<span>Anthony Burghiss</span>
											<span>A Catwork Orange</span>
										</h2>
									</div>
									<div class="bk-top"></div>
									<div class="bk-bottom"></div>
								</div>
								<div class="bk-info">
									<button class="bk-bookback">Flip</button>
									<button class="bk-bookview">View inside</button>
									<h3>
										<span>Anthony Burghiss</span>
										<span>A Catwork Orange</span>
									</h3>
									<p>
										Social prophecy? Black comedy? Study of freewill? A
										Clockwork Orange is all of these. It is also a
										dazzling experiment in language, as Burghiss creates
										a new language - 'meow', the cat slang of a
										not-too-distant future.
									</p>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div id="detailRight">
				<div id="detailSubject">{{ detail.subject }}</div>
				<div>{{ detail.count }} / {{ detail.limit }}</div>
				<div>{{ detail.description }}</div>
				<div id="openDiary">펼쳐보기</div>
			</div>
		</div>
	</v-dialog>
</template>

<script>
export default {
	props: { detail: {}, dialog: {} },
	data() {
		return {
			isOpen: false,
		};
	},
	mounted() {
		this.isOpen = this.dialog;
	},
};
</script>

<style>
@import "../../../utils/3DBookShowcase/css/component.css";
@import "../../../utils/3DBookShowcase/css/default.css";

#roomDetail {
	background-color: white;
	width: 100%;
	height: 500px;
}

#detailLeft {
	float: left;
	width: 50%;
	height: 100%;
}

#detailRight {
	float: right;
	width: 50%;
	padding: 20px;
}

#detailTitle {
	font-weight: 400;
}

#openDiary {
	height: 50px;
	width: 100%;
	background-color: rgb(250, 223, 153);
	margin-top: 10px;
	padding: 5px 10px 5px 10px;
	border-radius: 5px;
	cursor: pointer;
	line-height: 40px;
}
</style>
