from flask import Flask, render_template, Response, request, jsonify
import time
import json

app = Flask(__name__)
app.secret_key = 'super secret key'

# Placeholder: Replace with actual data source (database)
status_data = {
    'overall_status': 'Operational',
    'uptime': '99.9%'
}

def generate_status_updates():
    while True:
        # Placeholder: Simulate status changes (in real app, monitor system)
        time.sleep(5)
        #status_data['uptime'] = str(float(status_data['uptime']) - 0.001) # Degrade uptime
        yield f"data: {json.dumps(status_data)}\n\n"


@app.route('/status_stream')
def status_stream():
    return Response(generate_status_updates(), mimetype="text/event-stream")


@app.route('/')
def index():
    return render_template('status.html')


@app.route('/update_status', methods=['POST'])
def update_status():
    data = request.get_json()
    status_data['overall_status'] = data['overall_status']
    status_data['uptime'] = data['uptime']
    # In a real app, update the database here

    # Placeholder: Trigger SSE update (you'd need a more robust mechanism)
    return jsonify({'message': 'Status updated successfully'})

if __name__ == '__main__':
    app.run(debug=True)