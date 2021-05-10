FROM tensorflow/serving

LABEL maintainer="marshtupa18@gmail.com"

ADD serving_model /models/mask

ENV MODEL_NAME=mask

EXPOSE 8500/tcp