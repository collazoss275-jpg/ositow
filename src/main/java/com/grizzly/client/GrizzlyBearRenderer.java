package com.grizzly.client;

import com.grizzly.GrizzlyMod;
import com.grizzly.entity.GrizzlyBear;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GrizzlyBearRenderer extends MobRenderer<GrizzlyBear, GrizzlyBearModel> {

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(GrizzlyMod.MODID, "textures/entity/grizzly_bear.png");

    public GrizzlyBearRenderer(EntityRendererProvider.Context context) {
        super(context, new GrizzlyBearModel(context.bakeLayer(GrizzlyBearModel.LAYER)), 1.2F);
    }

    @Override
    public ResourceLocation getTextureLocation(GrizzlyBear entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(GrizzlyBear bear, PoseStack poseStack, float partialTick) {
        if (bear.isBaby()) {
            // Cría = adulto a la mitad (escala uniforme)
            poseStack.scale(0.5F, 0.5F, 0.5F);
            this.shadowRadius = 0.6F;
        } else {
            this.shadowRadius = 1.2F;
        }
        // El modelo mide del z -15 al 30 (centro en 7.5): lo centramos en el origen del oso
        // para que la caja de golpes cubra todo el cuerpo.
        poseStack.translate(0.0F, 0.0F, -0.46875F);
    }
}
