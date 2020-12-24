#!/usr/bin/env bash
root=""$( pwd )""

ruby --version
gem install bundler
bundle install --path=vendor/bundle

#git config --global user.email "android.com"
#git config --global user.name "Android Customers CircleCI"
