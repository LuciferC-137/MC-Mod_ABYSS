// Save this class in your mod and generate all required imports

/**
 * Made with Blockbench 5.0.7
 * Exported for Minecraft version 1.19 or later with Mojang mappings
 * @author Author
 */
public class ThryssarynAnimation {
	public static final AnimationDefinition mandible_idle = AnimationDefinition.Builder.withLength(2.0F).looping()
		.addAnimation("mandible_r", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 7.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("mandible_l", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, -7.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.build();

	public static final AnimationDefinition mandible_clack = AnimationDefinition.Builder.withLength(2.0F).looping()
		.addAnimation("mandible_r", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, -14.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(0.0F, -10.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.7083F, KeyframeAnimations.degreeVec(0.0F, -14.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("mandible_l", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 14.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.625F, KeyframeAnimations.degreeVec(0.0F, 10.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.7083F, KeyframeAnimations.degreeVec(0.0F, 14.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.build();

	public static final AnimationDefinition curiosity_look = AnimationDefinition.Builder.withLength(8.5F)
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(25.0F, -7.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.5F, KeyframeAnimations.degreeVec(25.0F, -7.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(8.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("head_main", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-30.0F, -27.5F, 7.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0833F, KeyframeAnimations.degreeVec(-30.0F, -27.5F, 7.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.3333F, KeyframeAnimations.degreeVec(-30.0F, -27.5F, 20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(-30.0F, -27.5F, 20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.5F, KeyframeAnimations.degreeVec(-30.0F, -27.5F, 20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.8333F, KeyframeAnimations.degreeVec(5.0F, 42.5F, 20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.125F, KeyframeAnimations.degreeVec(5.0F, 42.5F, 20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.4583F, KeyframeAnimations.degreeVec(5.0F, 42.5F, 20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.9167F, KeyframeAnimations.degreeVec(32.5F, 12.5F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(4.1667F, KeyframeAnimations.degreeVec(32.5F, 12.5F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(4.7083F, KeyframeAnimations.degreeVec(-5.0F, 12.5F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(5.2917F, KeyframeAnimations.degreeVec(-5.0F, 12.5F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(32.5F, 12.5F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(6.5417F, KeyframeAnimations.degreeVec(32.5F, 12.5F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(7.1667F, KeyframeAnimations.degreeVec(-5.0F, 12.5F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(7.8333F, KeyframeAnimations.degreeVec(-5.0F, 12.5F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(8.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.build();

	public static final AnimationDefinition body_idle = AnimationDefinition.Builder.withLength(4.0F).looping()
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.1F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-1.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("leg_bl", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, -1.8F, -1.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("leg_br", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 1.8F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("leg_mr", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(1.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("leg_ml", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(1.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("abdomen", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(1.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(-1.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.build();

	public static final AnimationDefinition crawl_position = AnimationDefinition.Builder.withLength(0.0F)
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(60.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -15.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("neck_head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-27.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("head_main", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(60.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -15.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("leg_bl", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-60.3706F, -11.62F, 14.357F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("leg_bl", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 1.0F, -1.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("end_bl", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -17.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("leg_br", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-60.3706F, 11.62F, -14.357F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("leg_br", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 1.0F, -1.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("end_br", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 17.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("leg_mr", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-60.7277F, 12.4278F, 57.4712F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("end_mr", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -27.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("leg_ml", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-60.7277F, -12.4278F, -57.4712F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("end_ml", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 27.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("leg_fl", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-103.0291F, -15.9985F, -100.8744F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("end_fl", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 37.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("leg_fr", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-103.0291F, 15.9985F, 100.8744F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("end_fr", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -37.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("abdomen", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.build();

	public static final AnimationDefinition stand2crawl = AnimationDefinition.Builder.withLength(1.0F)
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(60.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, -15.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("head_main", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("neck_head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-27.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(60.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, -15.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("leg_bl", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-60.3706F, -11.62F, 14.357F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("leg_bl", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 1.0F, -1.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("end_bl", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -17.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("leg_br", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-60.3706F, 11.62F, -14.357F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("leg_br", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 1.0F, -1.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("end_br", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 17.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("leg_mr", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-60.7277F, 12.4278F, 57.4712F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("end_mr", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -27.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("leg_ml", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-60.7277F, -12.4278F, -57.4712F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("end_ml", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 27.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("leg_fl", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-103.0291F, -15.9985F, -100.8744F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("end_fl", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 37.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("leg_fr", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-103.0291F, 15.9985F, 100.8744F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("end_fr", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -37.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("abdomen", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.build();

	public static final AnimationDefinition crawling = AnimationDefinition.Builder.withLength(2.0F).looping()
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(60.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(60.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -15.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, -15.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("head_main", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(-45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("neck_head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-27.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(-27.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(60.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(60.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -15.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, -15.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("leg_bl", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-59.4691F, -29.5506F, 15.5322F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-50.5974F, -9.7751F, -18.9314F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-51.1737F, 3.8451F, -7.9023F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.degreeVec(-60.3706F, -11.62F, 14.357F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(-59.4691F, -29.5506F, 15.5322F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("leg_bl", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 1.0F, -1.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 1.0F, -1.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("end_bl", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 25.56F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 32.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 32.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -8.75F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -8.75F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 25.56F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("leg_br", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-60.3706F, 11.62F, -14.357F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-59.4691F, 29.5506F, -15.5322F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-50.5974F, 9.7751F, 18.9314F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.3333F, KeyframeAnimations.degreeVec(-51.1737F, -3.8451F, 7.9023F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(-60.3706F, 11.62F, -14.357F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("leg_br", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 1.0F, -1.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 1.0F, -1.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("end_br", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 8.75F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -25.56F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -32.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -32.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 8.75F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 8.75F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("leg_mr", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-54.3215F, 32.1258F, 53.2994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-56.91F, 25.25F, 66.29F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-59.6777F, 16.886F, 78.1115F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-58.3574F, 2.3613F, 68.2353F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.degreeVec(-55.7923F, 20.2914F, 43.9501F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(-54.3215F, 32.1258F, 53.2994F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("end_mr", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.48F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -70.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -70.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -70.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -21.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -21.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.48F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("leg_ml", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-56.91F, -25.25F, -66.29F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-59.6777F, -16.886F, -78.1115F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-58.3574F, -2.3613F, -68.2353F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.3333F, KeyframeAnimations.degreeVec(-55.7923F, -20.2914F, -43.9501F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.degreeVec(-54.3215F, -32.1258F, -53.2994F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(-56.91F, -25.25F, -66.29F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("end_ml", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 70.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 70.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 21.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 21.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 70.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 70.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("leg_fl", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-103.5005F, -22.9795F, -119.1445F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-102.4213F, -7.6481F, -111.0722F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-95.4722F, -24.0034F, -73.8119F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.3333F, KeyframeAnimations.degreeVec(-92.7307F, -38.2289F, -89.7768F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(-103.5005F, -22.9795F, -119.1445F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("end_fl", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 75.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 26.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 26.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 75.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 75.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("leg_fr", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-98.01F, 32.45F, 105.96F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-103.5005F, 22.9795F, 119.1445F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-102.4213F, 7.6481F, 111.0722F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.3333F, KeyframeAnimations.degreeVec(-95.4722F, 24.0034F, 73.8119F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.degreeVec(-92.7307F, 38.2289F, 89.7768F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(-98.01F, 32.45F, 105.96F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("end_fr", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -80.42F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -75.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -26.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -26.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -75.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -80.42F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("abdomen", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(-37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.build();

	public static final AnimationDefinition walking = AnimationDefinition.Builder.withLength(2.0F).looping()
		.addAnimation("leg_bl", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(9.363F, -31.2646F, -17.6253F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.5014F, 10.8892F, -12.5291F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(3.3334F, 10.3843F, 2.7261F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.degreeVec(-1.5918F, -32.4653F, 2.9635F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(9.363F, -31.2646F, -17.6253F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("end_bl", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 25.56F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 32.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 32.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -8.75F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -8.75F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 25.56F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("leg_br", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(3.3334F, -10.3843F, -2.7261F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-1.5918F, 32.4653F, -2.9635F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(9.363F, 31.2646F, 17.6253F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.degreeVec(0.5014F, -10.8892F, 12.5291F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(3.3334F, -10.3843F, -2.7261F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("end_br", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 8.75F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 8.75F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -25.56F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -32.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -32.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 8.75F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("leg_mr", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-5.1316F, 11.8635F, 1.1268F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-6.6536F, 12.7125F, 18.6719F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(2.5427F, -7.4787F, 18.2991F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.3333F, KeyframeAnimations.degreeVec(10.0265F, -4.5869F, -0.5034F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(-5.1316F, 11.8635F, 1.1268F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("end_mr", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -35.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -35.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("leg_ml", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(2.5427F, 7.4787F, -18.2991F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(10.0265F, 4.5869F, 0.5034F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-5.1316F, -11.8635F, -1.1268F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.3333F, KeyframeAnimations.degreeVec(-6.6536F, -12.7125F, -18.6719F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(2.5427F, 7.4787F, -18.2991F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("end_ml", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 35.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 35.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 35.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.build();

	public static final AnimationDefinition holding_item = AnimationDefinition.Builder.withLength(0.5F)
		.addAnimation("leg_fl", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-87.7162F, 23.005F, -24.737F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("end_fl", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 32.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("leg_fr", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-87.7162F, -23.005F, 24.737F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("end_fr", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -32.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.build();

	public static final AnimationDefinition inspecting = AnimationDefinition.Builder.withLength(4.0F)
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("neck_head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("head_main", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.9167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 12.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.375F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 12.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, -5.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, -5.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.1667F, KeyframeAnimations.degreeVec(9.1296F, -4.9238F, 10.0374F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(9.1296F, -4.9238F, 10.0374F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("leg_fl", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(10.4815F, -6.3667F, 4.5486F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(10.4815F, -6.3667F, 4.5486F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("leg_fr", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(10.4815F, 6.3667F, -4.5486F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(10.4815F, 6.3667F, -4.5486F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.build();

	public static final AnimationDefinition playing_luth_position = AnimationDefinition.Builder.withLength(2.0F)
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-4.9574F, -0.6518F, -7.4718F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-5.2984F, -20.5716F, -5.66F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, -4.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("neck_head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("head_main", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(17.0108F, -3.841F, 14.5108F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(-15.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(-20.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, -4.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("leg_bl", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(17.4811F, 0.4352F, -4.9811F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(16.6967F, -1.3462F, -15.9821F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(17.0638F, -2.3587F, -19.8034F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(18.7095F, -10.9987F, -26.9432F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(19.9282F, -8.5142F, -34.1243F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(-23.1955F, -25.6004F, -15.4946F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-32.5692F, -35.4449F, 0.2358F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.degreeVec(-32.57F, -35.44F, 0.24F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(-36.5387F, -24.2149F, 8.8268F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("end_bl", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(9.408F, 3.4049F, -2.2197F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(9.3758F, 3.3202F, -1.8098F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(9.9907F, 0.434F, 15.0379F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(-17.51F, 0.43F, 15.04F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-30.01F, 0.43F, 15.04F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("leg_br", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(9.8876F, 2.0262F, 2.8932F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(10.5619F, 4.75F, 16.0979F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(-15.9448F, 1.6674F, -14.2059F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-18.0302F, 10.9278F, -9.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("end_br", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(12.0868F, 3.2113F, -14.6599F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(-54.7684F, 6.1378F, 4.3184F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-47.5018F, 0.002F, 0.0018F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("leg_mr", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(-4.4715F, -7.6596F, 33.0672F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.degreeVec(-4.47F, -7.66F, 33.07F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(-41.9738F, -6.6363F, -0.0451F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("leg_mr", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(1.5417F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.posVec(-0.5F, -2.2F, -0.65F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("end_mr", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(22.4229F, 1.9113F, -4.6211F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(29.0536F, 6.6061F, -16.2398F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(16.6483F, 24.9903F, -59.2401F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5417F, KeyframeAnimations.degreeVec(16.65F, 24.99F, -59.24F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(11.2134F, 30.7047F, -71.9758F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("leg_ml", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.1667F, KeyframeAnimations.degreeVec(11.2168F, -1.7397F, -6.5025F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(43.6272F, -12.4009F, 3.2363F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.degreeVec(45.5778F, -14.9912F, 5.005F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.3333F, KeyframeAnimations.degreeVec(45.9093F, -10.8519F, 1.1727F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(45.4312F, -7.0885F, -2.1544F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5833F, KeyframeAnimations.degreeVec(45.43F, -7.09F, -2.15F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(7.904F, -14.0279F, -1.4035F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("end_ml", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(-55.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(-57.2155F, 16.7477F, 15.4452F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4583F, KeyframeAnimations.degreeVec(-57.22F, 16.75F, 15.45F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(-67.2891F, 30.7205F, 21.4399F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("leg_fl", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(-2.0737F, -8.5681F, -33.0619F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(23.7531F, -40.3094F, -36.263F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5833F, KeyframeAnimations.degreeVec(23.7531F, -40.3094F, -36.263F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7917F, KeyframeAnimations.degreeVec(47.933F, -27.0165F, -52.6916F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0417F, KeyframeAnimations.degreeVec(-37.1209F, -25.5016F, -35.2554F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-37.12F, -25.5F, -35.26F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(-52.3172F, 6.2936F, -28.1516F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("end_fl", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 62.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 80.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.degreeVec(-37.5F, 0.0F, 57.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-37.5F, 0.0F, 57.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(-3.605F, 17.7209F, 82.1098F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("leg_fr", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(-27.3358F, -10.278F, 52.9175F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.625F, KeyframeAnimations.degreeVec(-23.4439F, -36.9276F, 47.9832F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(31.868F, -35.3229F, -11.9825F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("end_fr", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -32.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -32.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(-34.0724F, -8.5373F, -44.8796F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("abdomen", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.build();

	public static final AnimationDefinition playing_luth_warden = AnimationDefinition.Builder.withLength(52.0F)
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-5.3F, -20.57F, -5.66F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -4.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("neck_head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(10.0F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("head_main", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(17.0108F, -3.841F, 14.5108F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0417F, KeyframeAnimations.degreeVec(14.479F, 1.0011F, 15.7614F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(10.0F, KeyframeAnimations.degreeVec(21.979F, 1.0011F, 15.7614F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(15.0417F, KeyframeAnimations.degreeVec(19.7185F, -8.2677F, 11.9959F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(26.7917F, KeyframeAnimations.degreeVec(7.2185F, -8.2677F, 11.9959F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(31.875F, KeyframeAnimations.degreeVec(7.35F, -5.85F, 11.23F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(33.0833F, KeyframeAnimations.degreeVec(17.73F, -5.72F, 11.33F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(37.0417F, KeyframeAnimations.degreeVec(7.35F, -5.85F, 11.23F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(52.0F, KeyframeAnimations.degreeVec(17.0108F, -3.841F, 14.5108F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(89.375F, KeyframeAnimations.degreeVec(-12.669F, -3.3903F, 10.8892F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -4.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("leg_bl", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-36.54F, -24.21F, 8.83F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("end_bl", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-30.01F, 0.43F, 15.04F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("leg_br", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-18.03F, 10.93F, -9.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("end_br", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-47.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("leg_mr", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-41.97F, -6.64F, -0.05F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("leg_mr", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(-0.5F, -2.2F, -0.65F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("end_mr", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(11.21F, 30.7F, -71.98F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(-8.0087F, 32.4396F, -89.487F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(1.7299F, 33.2694F, -71.6395F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(8.125F, KeyframeAnimations.degreeVec(11.21F, 30.7F, -71.98F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(8.375F, KeyframeAnimations.degreeVec(-8.0087F, 32.4396F, -89.487F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(9.625F, KeyframeAnimations.degreeVec(1.7299F, 33.2694F, -71.6395F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(16.0F, KeyframeAnimations.degreeVec(11.21F, 30.7F, -71.98F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(16.25F, KeyframeAnimations.degreeVec(-8.0087F, 32.4396F, -89.487F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(17.5F, KeyframeAnimations.degreeVec(1.7299F, 33.2694F, -71.6395F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(24.0F, KeyframeAnimations.degreeVec(11.21F, 30.7F, -71.98F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(24.25F, KeyframeAnimations.degreeVec(-8.0087F, 32.4396F, -89.487F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(25.5F, KeyframeAnimations.degreeVec(1.7299F, 33.2694F, -71.6395F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("leg_ml", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(7.9F, -14.03F, -1.4F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("end_ml", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-67.29F, 30.72F, 21.44F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("leg_fl", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-52.32F, 6.29F, -28.15F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("end_fl", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.4167F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0833F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.375F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.7083F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.2917F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5417F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0833F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(4.4167F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.6667F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(4.9167F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.2083F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(5.5F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.7083F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(5.9167F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.25F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(6.5833F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.875F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(7.2083F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(7.5F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(7.7917F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(8.0417F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(8.2917F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(8.5833F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(8.9167F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(9.1667F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(9.4167F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(9.7083F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(10.0F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(10.2083F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(10.4167F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(10.75F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(11.0833F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(11.375F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(11.7083F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.0F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(12.2917F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.5417F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(12.7917F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(13.0833F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(13.4167F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(13.6667F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(13.9167F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(14.2083F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(14.5F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(14.7083F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(14.9167F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(15.25F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(15.5833F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(15.875F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(16.2083F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(16.5F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(16.7917F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(17.0417F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(17.2917F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(17.5833F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(17.9167F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(18.1667F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(18.4167F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(18.7083F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(19.0F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(19.2083F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(19.4167F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(19.75F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(20.0833F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(20.375F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(20.7083F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(21.0F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(21.2917F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(21.5417F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(21.7917F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(22.0833F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(22.4167F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(22.6667F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(22.9167F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(23.2083F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(23.5F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(23.7083F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(23.9167F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(24.25F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(24.5833F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(24.875F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(25.2083F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(25.5F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(25.7917F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(26.0417F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(26.2917F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(26.5833F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(26.9167F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(27.1667F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(27.4167F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(27.7083F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(28.0F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(28.2083F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(28.4167F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(28.75F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(29.0833F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(29.375F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(29.7083F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(30.0F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(30.2917F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(30.5417F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(30.7917F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(31.0833F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(31.4167F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(31.6667F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(31.9167F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(32.2083F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(32.5F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(32.7083F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(32.9167F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(33.25F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(33.5833F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(33.875F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(34.2083F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(34.5F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(34.7917F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(35.0417F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(35.2917F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(35.5833F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(35.9167F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(36.1667F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(36.4167F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(36.7083F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(37.0F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(37.2083F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(37.4167F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(37.7083F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(38.0F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(38.3333F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(38.9167F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(39.5F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(40.2083F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(41.1667F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(42.2083F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(43.25F, KeyframeAnimations.degreeVec(3.89F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(45.0F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(47.375F, KeyframeAnimations.degreeVec(-8.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(50.3333F, KeyframeAnimations.degreeVec(-3.61F, 17.72F, 82.11F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("leg_fr", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(31.87F, -35.32F, -11.98F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("end_fr", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-34.07F, -8.54F, -44.88F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(-37.1761F, -18.2191F, -53.0222F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(-49.6761F, -18.2191F, -53.0222F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-42.0247F, -2.643F, -40.2215F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(8.0F, KeyframeAnimations.degreeVec(-37.1761F, -18.2191F, -53.0222F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(8.4167F, KeyframeAnimations.degreeVec(-49.6761F, -18.2191F, -53.0222F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(10.0417F, KeyframeAnimations.degreeVec(-42.0247F, -2.643F, -40.2215F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(15.8333F, KeyframeAnimations.degreeVec(-37.1761F, -18.2191F, -53.0222F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(16.25F, KeyframeAnimations.degreeVec(-49.6761F, -18.2191F, -53.0222F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(17.875F, KeyframeAnimations.degreeVec(-42.0247F, -2.643F, -40.2215F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(24.1667F, KeyframeAnimations.degreeVec(-37.1761F, -18.2191F, -53.0222F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(24.5833F, KeyframeAnimations.degreeVec(-49.6761F, -18.2191F, -53.0222F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(26.2083F, KeyframeAnimations.degreeVec(-42.0247F, -2.643F, -40.2215F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(28.25F, KeyframeAnimations.degreeVec(-42.0247F, -2.643F, -40.2215F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(30.9583F, KeyframeAnimations.degreeVec(-74.2577F, -2.4529F, -40.1924F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(32.1667F, KeyframeAnimations.degreeVec(-73.5205F, -27.3792F, -44.0077F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(32.75F, KeyframeAnimations.degreeVec(-31.0205F, -27.3792F, -44.0077F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(35.4583F, KeyframeAnimations.degreeVec(-39.7579F, -21.5807F, -35.0609F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("abdomen", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.build();
}