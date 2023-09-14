#!/usr/bin/env bash
.  ./set_command.sh
perf stat $DEMO_COMMAND &
export PERF_PID=$!
./psrecord-master/scripts/psrecord $PERF_PID --plot plot$(date +%s).png --include-children &
while [ "$(curl -s -o /dev/null -L -w ''%{http_code}'' http://localhost:8080/hello)" != "200" ]; do sleep 0.001; done
echo "Simulating some load..."
for i in $(seq 20)
do
  curl -s -o /dev/null -L -w "." http://localhost:8080/hello
  sleep 0.5
done
export MY_PID="$(pgrep -P $PERF_PID)"
kill $MY_PID
sleep 1