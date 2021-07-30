import paddle
from mprDeblur.apps.mpr_predictor import MPRPredictor
import sys

sys.path.append('ppgan')

paddle.disable_static()


def predict(images_path):
    output_path = 'D:/predeblur/'

    images_path = images_path
    weight_path = 'mprDeblur/pro1.pdparams'
    deblur_moudle = MPRPredictor(images_path=images_path,
                                 output_path=output_path,
                                 weight_path=weight_path,
                                 seed=None,
                                 task='Deblurring')
    deblur_moudle.run()
