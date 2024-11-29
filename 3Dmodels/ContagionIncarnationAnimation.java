// Save this class in your mod and generate all required imports

/**
 * Made with Blockbench 4.11.2
 * Exported for Minecraft version 1.19 or later with Mojang mappings
 * @author Author
 */
public class ContagionIncarnationAnimation {
	public static final AnimationDefinition walking = AnimationDefinition.Builder.withLength(2.25F).looping()
		.addAnimation("LEG_LEFT_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FORELEG_L_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FEET_L_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("LEG_LEFT_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FORELEG_L_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FEET_L_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("LEG_LEFT_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FORELEG_L_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FEET_L_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("LEG_LEFT_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -20.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FORELEG_L_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FEET_L_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("LEG_RIGHT_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 7.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, -20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 7.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, -20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 7.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, -20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 7.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FORELEG_R_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FEET_R_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("LEG_RIGHT_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 7.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, -20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 7.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, -20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 7.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, -20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FORELEG_R_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FEET_R_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("LEG_RIGHT_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 7.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, -20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 7.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, -20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 7.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, -20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FORELEG_R_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FEET_R_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("LEG_RIGHT_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 7.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, -20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 7.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, -20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 7.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, -20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FORELEG_R_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FEET_R_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.build();

	public static final AnimationDefinition crawling = AnimationDefinition.Builder.withLength(2.25F).looping()
		.addAnimation("LEG_LEFT_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FORELEG_L_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FEET_L_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("LEG_LEFT_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FORELEG_L_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FEET_L_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("LEG_LEFT_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-27.5F, 32.5F, -7.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(-27.5F, -15.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-20.0F, 2.5F, -27.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(-27.5F, 32.5F, -7.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-27.5F, -15.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(-20.0F, 2.5F, -27.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-27.5F, 32.5F, -7.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(-27.5F, -15.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(-20.0F, 2.5F, -27.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(-27.5F, 32.5F, -7.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FORELEG_L_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FEET_L_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("LEG_LEFT_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-27.5F, 12.5F, -32.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(-27.5F, 12.5F, 2.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-27.5F, -20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(-27.5F, 12.5F, -32.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-27.5F, 12.5F, 2.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(-27.5F, -20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-27.5F, 12.5F, -32.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(-27.5F, 12.5F, 2.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(-27.5F, -20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(-27.5F, 12.5F, -32.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FORELEG_L_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FEET_L_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("LEG_RIGHT_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 7.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, -20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 7.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, -20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 7.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, -20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 7.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FORELEG_R_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FEET_R_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("LEG_RIGHT_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 7.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, -20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 7.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, -20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 7.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, -20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FORELEG_R_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FEET_R_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("LEG_RIGHT_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-27.5F, 20.0F, -3.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(-27.5F, -20.0F, 34.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-27.5F, -20.0F, -3.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(-27.5F, 20.0F, -3.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-27.5F, -20.0F, 34.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(-27.5F, -20.0F, -3.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-27.5F, 20.0F, -3.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(-27.5F, -20.0F, 34.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(-27.5F, -20.0F, -3.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(-27.5F, 20.0F, -3.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FORELEG_R_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FEET_R_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("LEG_RIGHT_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-25.0F, -12.5F, -0.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(-27.5F, 20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-35.0F, -12.5F, 19.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(-25.0F, -12.5F, -0.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-27.5F, 20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(-35.0F, -12.5F, 19.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-25.0F, -12.5F, -0.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(-27.5F, 20.0F, -13.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(-35.0F, -12.5F, 19.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(-25.0F, -12.5F, -0.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FORELEG_R_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 33.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FEET_R_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -55.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FRONT_BODY", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(27.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.4167F, KeyframeAnimations.degreeVec(27.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(27.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(27.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("ARM_RIGHT", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -7.5F, 7.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, -45.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, -7.5F, 7.5F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("FOREARM_R", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(21.0F, 11.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.875F, KeyframeAnimations.degreeVec(96.0F, 11.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("ARM_LEFT", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -7.5F, 7.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.4583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.degreeVec(0.0F, 45.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, -7.5F, 7.5F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("FOREARM_L", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.degreeVec(0.0F, -32.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(70.0F, -32.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("UPPER", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9167F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("SECTION_5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("SECTION_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9167F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("SECTION_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("SECTION_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9167F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("SECTION_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("HEAD", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("HEAD", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.build();

	public static final AnimationDefinition breathing = AnimationDefinition.Builder.withLength(6.0F).looping()
		.addAnimation("BELLY", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.0F, KeyframeAnimations.scaleVec(1.05F, 1.05F, 1.05F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(6.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("CHESTPLATE", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(3.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("UPPER", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(1.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("SECTION_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(1.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("SECTION_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(1.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("SECTION_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(1.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("SECTION_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(1.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.build();

	public static final AnimationDefinition mane_and_head_ambient = AnimationDefinition.Builder.withLength(5.0F).looping()
		.addAnimation("UPPER", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.5F, KeyframeAnimations.degreeVec(3.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.75F, KeyframeAnimations.degreeVec(3.0F, 4.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(5.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("SECTION_5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.5F, KeyframeAnimations.degreeVec(3.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.75F, KeyframeAnimations.degreeVec(3.0F, 4.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(5.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("SECTION_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.5F, KeyframeAnimations.degreeVec(3.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.75F, KeyframeAnimations.degreeVec(3.0F, 4.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(5.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("SECTION_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.5F, KeyframeAnimations.degreeVec(3.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.75F, KeyframeAnimations.degreeVec(3.0F, 4.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(5.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("SECTION_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.5F, KeyframeAnimations.degreeVec(3.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.75F, KeyframeAnimations.degreeVec(3.0F, 4.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(5.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("SECTION_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.5F, KeyframeAnimations.degreeVec(3.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.75F, KeyframeAnimations.degreeVec(3.0F, 4.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(5.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("HEAD", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(10.0F, 10.0F, 10.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-10.0F, -10.0F, -10.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.375F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 30.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(5.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("MANE", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.9583F, KeyframeAnimations.degreeVec(-30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(5.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.build();

	public static final AnimationDefinition leg_spasm = AnimationDefinition.Builder.withLength(10.0F).looping()
		.addAnimation("LEG_LEFT_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.4583F, KeyframeAnimations.degreeVec(0.0F, -10.0F, -20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.6667F, KeyframeAnimations.degreeVec(0.0F, 10.0F, -20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.875F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(10.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FORELEG_L_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.2917F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.375F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.5417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.625F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(10.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("LEG_RIGHT_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(8.7083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(8.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(9.1667F, KeyframeAnimations.degreeVec(0.0F, -10.0F, 20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(9.375F, KeyframeAnimations.degreeVec(0.0F, 10.0F, -20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(9.5833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(10.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FORELEG_R_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(9.0833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(9.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(9.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(9.4167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(10.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("LEG_RIGHT_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.0833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.5417F, KeyframeAnimations.degreeVec(0.0F, -10.0F, 20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.75F, KeyframeAnimations.degreeVec(0.0F, 10.0F, -20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(10.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FORELEG_R_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.4167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(10.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.build();

	public static final AnimationDefinition death = AnimationDefinition.Builder.withLength(10.0F)
		.addAnimation("TAIL", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.posVec(0.0F, -3.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.2917F, KeyframeAnimations.posVec(0.0F, -3.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("SECTION_6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, 7.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("SECTION_7", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, 7.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("LEG_LEFT_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 12.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FORELEG_L_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -75.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FEET_L_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("LEG_RIGHT_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -20.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FORELEG_R_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 92.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("SECTION_8", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, -2.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("LEG_LEFT_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 17.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FORELEG_L_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -85.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("LEG_RIGHT_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -20.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FORELEG_R_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 90.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("SECTION_9", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(-2.5F, -7.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("SECTION_10", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, -10.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("SECTION_11", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(2.5F, -5.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("SECTION_12", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(-2.5F, 5.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("SECTION_13", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(2.5F, 7.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("SECTION_14", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(-2.5F, 12.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("SECTION_15", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(-2.5F, 15.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("SECTION_16", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(2.5F, 12.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("SECTION_17", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(-2.0F, 27.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("SECTION_18", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(-5.0F, 15.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("END", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(5.0F, 30.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FRONT_BODY", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(7.0F, KeyframeAnimations.degreeVec(60.0F, 12.5F, 45.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FRONT_BODY", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.posVec(0.0F, -3.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.2917F, KeyframeAnimations.posVec(0.0F, -3.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(7.0F, KeyframeAnimations.posVec(0.0F, -3.0F, 3.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("TORSO", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("ARM_RIGHT", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, -27.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.4583F, KeyframeAnimations.degreeVec(-1.33F, -12.74F, -1.21F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(7.0F, KeyframeAnimations.degreeVec(-27.5F, -32.5F, -25.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("ARMBASE_R", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FOREARM_R", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(7.0F, KeyframeAnimations.degreeVec(62.5F, 0.0F, 25.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FINGER_R_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(5.0F, -12.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(7.0F, KeyframeAnimations.degreeVec(10.0F, 25.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FOREFINGER_R_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, -52.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(7.0F, KeyframeAnimations.degreeVec(0.0F, 57.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FINGER_R_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(7.0F, KeyframeAnimations.degreeVec(40.0F, 37.5F, 47.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FOREFINGER_R_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(-25.0F, -12.5F, 12.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(7.0F, KeyframeAnimations.degreeVec(37.5F, -5.0F, 15.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FINGER_R_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(-20.0F, 2.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FOREFINGER_R_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(-12.5F, 22.5F, 12.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FINGER_R_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(5.0F, 22.5F, -20.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(7.0F, KeyframeAnimations.degreeVec(5.0F, -12.5F, -20.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FOREFINGER_R_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, 57.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("LEG_LEFT_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(-37.5F, -17.5F, 12.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(7.0F, KeyframeAnimations.degreeVec(-2.5F, -50.0F, -25.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FORELEG_L_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -80.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(7.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -87.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FEET_L_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(7.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 20.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("LEG_RIGHT_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(-27.5F, 0.0F, -5.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(7.0F, KeyframeAnimations.degreeVec(-25.0F, -47.5F, -57.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FEET_R_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 57.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(7.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 35.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("LEG_RIGHT_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(12.5F, 0.0F, -42.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(7.0F, KeyframeAnimations.degreeVec(25.0F, -40.0F, -75.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("FORELEG_R_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 77.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("LEG_LEFT_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(-10.0F, -7.5F, 7.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(7.0F, KeyframeAnimations.degreeVec(-42.5F, -37.5F, -15.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FORELEG_L_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -70.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(7.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -87.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("ARM_LEFT", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, 47.5F, 30.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(0.0F, 47.5F, 30.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.9583F, KeyframeAnimations.degreeVec(0.0F, 47.5F, -10.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(3.9583F, KeyframeAnimations.degreeVec(14.47F, -20.14F, -31.77F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(7.0F, KeyframeAnimations.degreeVec(12.5F, -20.0F, -22.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FOREARM_L", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(-2.5F, -7.5F, -47.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(7.0F, KeyframeAnimations.degreeVec(-72.5F, -12.5F, 15.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FINGER_L_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, 15.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FINGER_L_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FOREFINGER_L_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(-7.5F, 2.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FINGER_L_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(15.0F, 10.0F, -22.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FOREFINGER_L_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(22.5F, 15.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FINGER_L_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(5.0F, -15.0F, -10.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FOREFINGER_L_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125F, KeyframeAnimations.degreeVec(2.5F, 5.0F, -15.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("UPPER", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5833F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-7.5F, 2.5F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5F, KeyframeAnimations.degreeVec(-7.5F, -2.5F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(7.0F, KeyframeAnimations.degreeVec(20.0F, -20.0F, 25.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("UPPER", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(7.0F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("SECTION_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5833F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-7.5F, 2.5F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5F, KeyframeAnimations.degreeVec(-7.5F, -2.5F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(7.0417F, KeyframeAnimations.degreeVec(2.5F, -2.5F, 5.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("SECTION_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5833F, KeyframeAnimations.degreeVec(-15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-15.0F, 2.5F, 7.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5F, KeyframeAnimations.degreeVec(-15.0F, -2.5F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(7.0417F, KeyframeAnimations.degreeVec(0.0F, 2.5F, 17.5F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("SECTION_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5833F, KeyframeAnimations.degreeVec(-12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-12.5F, 2.5F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5F, KeyframeAnimations.degreeVec(-12.5F, -2.5F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(7.0417F, KeyframeAnimations.degreeVec(-40.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("SECTION_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5833F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-7.5F, 2.5F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5F, KeyframeAnimations.degreeVec(-7.5F, -2.5F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(7.0417F, KeyframeAnimations.degreeVec(-32.5F, 10.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("JAW", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.7917F, KeyframeAnimations.degreeVec(26.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FORELEG_R_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(7.0F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 65.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FEET_R_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 27.5F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(7.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 27.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("FEET_L_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(7.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 20.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("HEAD", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(7.0417F, KeyframeAnimations.degreeVec(-32.5F, 20.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("HEAD", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(7.0417F, KeyframeAnimations.posVec(0.0F, 0.0F, 2.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.build();

	public static final AnimationDefinition arm_ambient = AnimationDefinition.Builder.withLength(4.0F).looping()
		.addAnimation("ARM_RIGHT", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -25.0F, -15.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, -20.0F, -17.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, -25.0F, -15.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("FOREARM_R", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(22.5F, 7.5F, 7.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(30.0F, -15.0F, 17.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(22.5F, 7.5F, 7.5F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("FINGER_R_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 7.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("FOREFINGER_R_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 7.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("FINGER_R_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.degreeVec(0.0F, 7.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("FOREFINGER_R_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.degreeVec(0.0F, 7.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("FINGER_R_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(0.0F, 7.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("FOREFINGER_R_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(0.0F, 7.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("FINGER_R_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.6667F, KeyframeAnimations.degreeVec(0.0F, 7.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("FOREFINGER_R_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.6667F, KeyframeAnimations.degreeVec(0.0F, 7.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("ARM_LEFT", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 17.5F, 15.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 24.0F, 22.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 17.5F, 15.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("FOREARM_L", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -12.5F, 7.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, -17.5F, 7.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, -12.5F, 7.5F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("FINGER_L_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(7.5F, -5.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.75F, KeyframeAnimations.degreeVec(7.5F, 5.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("FOREFINGER_L_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(7.5F, -5.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("FINGER_L_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(7.5F, -5.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("FOREFINGER_L_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(7.5F, -5.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("FINGER_L_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(7.5F, -5.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("FOREFINGER_L_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(7.5F, -5.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("FINGER_L_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4167F, KeyframeAnimations.degreeVec(7.5F, -5.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("FOREFINGER_L_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4167F, KeyframeAnimations.degreeVec(7.5F, -5.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.build();

	public static final AnimationDefinition scream = AnimationDefinition.Builder.withLength(4.9583F)
		.addAnimation("SECTION_6", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(-12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("SECTION_7", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(-20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("LEG_LEFT_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("LEG_RIGHT_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("SECTION_8", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("LEG_LEFT_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("LEG_RIGHT_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("FRONT_BODY", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("FRONT_BODY", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.posVec(0.0F, 3.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9583F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("ARMBASE_R", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(-15.0F, -47.5F, 10.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(40.0F, -47.5F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("FOREARM_R", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(-30.0F, 40.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("LEG_LEFT_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 30.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(-40.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("FORELEG_L_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -37.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("LEG_RIGHT_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(2.5F, 0.0F, -30.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(-40.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("FORELEG_R_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 37.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("LEG_RIGHT_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -22.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("FORELEG_R_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 17.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("LEG_LEFT_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 22.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("FORELEG_L_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -17.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("ARMBASE_L", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(-15.0F, 47.5F, -10.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(40.0F, 47.5F, 7.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("FOREARM_L", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(-30.0F, -40.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("SECTION_5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 7.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("SECTION_4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 7.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4583F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("SECTION_3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, 7.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4583F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("SECTION_2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-20.0F, 0.0F, 7.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4583F, KeyframeAnimations.degreeVec(-20.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(-20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("SECTION_1", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-17.5F, -2.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4583F, KeyframeAnimations.degreeVec(-17.5F, 2.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(-17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7917F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("JAW", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.2917F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.375F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.9583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.build();
}